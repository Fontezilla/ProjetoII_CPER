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
        Integer setorPrincipal = null;
        Set<Integer> setoresAssociados = new HashSet<>();

        switch (type) {
            case CLIENTE: {
                Cliente cliente = clienteRepository.findByEmail(request.getEmail())
                        .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));

                if (!cliente.getPassword().equals(request.getPassword())) {
                    throw new RuntimeException("Password inválida");
                }

                token = jwtUtil.generateToken(cliente.getEmail(), type, null, null);
                break;
            }

            case FUNCIONARIO: {
                Funcionario funcionario = funcionarioRepository.findByEmail(request.getEmail())
                        .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));

                if (!funcionario.getPassword().equals(request.getPassword())) {
                    throw new RuntimeException("Password inválida");
                }

                if (funcionario.getDepartamento() != null) {
                    setorPrincipal = funcionario.getDepartamento().getSetor();
                }

                 setoresAssociados = funcionario.getDepartamentos()
                        .stream()
                        .map(Departamento::getSetor).collect(Collectors.toSet());

                token = jwtUtil.generateToken(funcionario.getEmail(), type, setorPrincipal, setoresAssociados);
                break;
            }

            default:
                throw new RuntimeException("Tipo de utilizador inválido");
        }

        return new LoginResponseDto(token, type, setorPrincipal, setoresAssociados.isEmpty() ? null : setoresAssociados);
    }
}
