package com.example.cper_core.services;

import com.example.cper_core.dtos.login.LoginRequestDto;
import com.example.cper_core.dtos.login.LoginResponseDto;
import com.example.cper_core.entities.Cliente;
import com.example.cper_core.entities.Departamento;
import com.example.cper_core.entities.Funcionario;
import com.example.cper_core.enums.JwtTipoUtilizador;
import com.example.cper_core.repositories.ClienteRepository;
import com.example.cper_core.repositories.FuncionarioRepository;
import com.example.cper_core.utils.JwtUtils;
import com.example.cper_core.utils.PasswordUtils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthService {

    private final ClienteRepository clienteRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final JwtUtils jwtUtil;

    public AuthService(ClienteRepository clienteRepository,
                       FuncionarioRepository funcionarioRepository,
                       JwtUtils jwtUtil) {
        this.clienteRepository = clienteRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponseDto login(JwtTipoUtilizador type, LoginRequestDto request) {
        String token;
        Integer setorPrincipal = -1;
        Set<Integer> setoresAssociados = new HashSet<>();

        String passwordInput = request.getPassword();

        switch (type) {
            case CLIENTE -> {
                Cliente cliente = clienteRepository.findByEmail(request.getEmail())
                        .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));

                String storedPassword = cliente.getPassword();
                boolean autenticado = false;

                if (PasswordUtils.isHashed(storedPassword)) {
                    autenticado = PasswordUtils.checkPassword(passwordInput, storedPassword);
                } else if (passwordInput.equals(storedPassword)) {
                    autenticado = true;
                    cliente.setPassword(PasswordUtils.hashPassword(passwordInput));
                    clienteRepository.save(cliente);
                }

                if (!autenticado) {
                    throw new RuntimeException("Password inválida");
                }

                token = jwtUtil.generateToken(
                        cliente.getEmail(),
                        cliente.getNome(),
                        cliente.getId(),
                        type,
                        setorPrincipal,
                        setoresAssociados
                );
            }

            case FUNCIONARIO -> {
                Funcionario funcionario = funcionarioRepository.findByEmail(request.getEmail())
                        .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));

                String storedPassword = funcionario.getPassword();
                boolean autenticado = false;

                if (PasswordUtils.isHashed(storedPassword)) {
                    autenticado = PasswordUtils.checkPassword(passwordInput, storedPassword);
                } else if (passwordInput.equals(storedPassword)) {
                    autenticado = true;
                    funcionario.setPassword(PasswordUtils.hashPassword(passwordInput));
                    funcionarioRepository.save(funcionario);
                }

                if (!autenticado) {
                    throw new RuntimeException("Password inválida");
                }

                if (funcionario.getDepartamento() != null) {
                    setorPrincipal = funcionario.getDepartamento().getSetor();
                }

                setoresAssociados = funcionario.getDepartamentos().stream()
                        .map(Departamento::getSetor)
                        .collect(Collectors.toSet());

                token = jwtUtil.generateToken(
                        funcionario.getEmail(),
                        funcionario.getNome(),
                        funcionario.getId(),
                        type,
                        setorPrincipal,
                        setoresAssociados
                );
            }

            default -> throw new RuntimeException("Tipo de utilizador inválido");
        }

        return new LoginResponseDto(token, jwtUtil.getExpiracao(token));
    }
}