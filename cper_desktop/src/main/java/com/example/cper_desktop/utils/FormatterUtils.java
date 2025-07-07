package com.example.cper_desktop.utils;

import com.example.cper_core.dtos.cliente.ClienteDetailsExtendedDto;
import com.example.cper_core.dtos.endereco.EnderecoDto;
import com.example.cper_core.dtos.funcionario.FuncionarioDetailsExtendedDto;
import com.example.cper_core.dtos.morada.MoradaSimplesDto;
import com.example.cper_core.dtos.endereco.EnderecoDetailsExtendedDto;
import com.example.cper_core.services.interfaces.IEnderecoService;
import com.example.cper_core.services.interfaces.ILocalidadeService;
import com.example.cper_core.services.interfaces.IMoradaService;
import javafx.scene.control.*;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class FormatterUtils {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final NumberFormat NUMBER_FORMATTER = NumberFormat.getNumberInstance(Locale.forLanguageTag("pt-PT"));
    private static final NumberFormat CURRENCY_FORMATTER = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-PT"));

    public static String date(OffsetDateTime date) {
        return date == null ? "N/A" : date.toLocalDate().format(DATE_FORMATTER);
    }

    public static String decimal(BigDecimal valor, String sufixo) {
        if (valor == null) return "N/A";
        NUMBER_FORMATTER.setMinimumFractionDigits(2);
        NUMBER_FORMATTER.setMaximumFractionDigits(2);
        return NUMBER_FORMATTER.format(valor) + (sufixo != null ? " " + sufixo : "");
    }

    public static String currency(BigDecimal valor) {
        return valor == null ? "N/A" : CURRENCY_FORMATTER.format(valor);
    }

    public static String value(Object obj) {
        return (obj == null || (obj instanceof String s && s.isBlank())) ? "N/A" : obj.toString();
    }

    public static void configField(TextField campo, boolean editavel) {
        campo.setEditable(editavel);
        campo.getStyleClass().removeAll("editable", "readonly");
        campo.getStyleClass().add(editavel ? "editable" : "readonly");
    }

    public static void configField(ComboBox<?> comboBox, boolean editavel) {
        comboBox.setDisable(!editavel);
        comboBox.getStyleClass().removeAll("editable", "readonly");
        comboBox.getStyleClass().add(editavel ? "editable" : "readonly");
    }

    public static void configField(DatePicker datePicker, boolean editavel) {
        datePicker.setDisable(!editavel);
        datePicker.getStyleClass().removeAll("editable", "readonly");
        datePicker.getStyleClass().add(editavel ? "editable" : "readonly");
    }

    public static String formatSimpleAddress(EnderecoDto idEndereco, String nPorta, IEnderecoService enderecoService, ILocalidadeService localidadeService) {
        if (idEndereco == null || nPorta == null) return "N/A";
        try {
            EnderecoDetailsExtendedDto e = enderecoService.getExtendedById(idEndereco.getId());
            if (e == null || e.getLocalidade() == null || e.getLocalidade().getId() == null) return "N/A";
            var l = localidadeService.getExtendedById(e.getLocalidade().getId());
            if (l == null) return "N/A";
            return e.getRua() + ", nº" + nPorta + ", " + l.getCodigoPostal() + " " + l.getNome();
        } catch (Exception ex) {
            return "N/A";
        }
    }

    public static boolean validFormatAddress(String morada) {
        return morada != null && morada.matches(".+,\\s*nº\\d+,\\s*\\d{4}-\\d{3}\\s+.+");
    }

    public static void validateAddress(String morada, StringBuilder erros, TextField campo) {
        if (!validFormatAddress(morada)) {
            erros.append(" • morada: A morada deve estar no formato \"Rua, nº123, 1000-001 Localidade\"\n");
            StyleUtils.markInvalid(campo);
        }
    }


    public static <T> T getDetails(
            T dto,
            String email,
            String telefone,
            String nPorta,
            String moradaCompleta,
            boolean moradaValida,
            IMoradaService moradaService,
            Function<EnderecoDetailsExtendedDto, EnderecoDto> enderecoConverter,
            BiConsumer<T, EnderecoDto> setEndereco,
            BiConsumer<T, String> setNPorta
    ) {
        if (dto == null) return null;

        if (dto instanceof FuncionarioDetailsExtendedDto f) {
            f.setEmail(email);
            f.setTelefone(telefone);
        }
        if (dto instanceof ClienteDetailsExtendedDto c) {
            c.setEmail(email);
            c.setTelefone(telefone);
        }

        if (moradaValida) {
            MoradaSimplesDto moradaDto = extractSimpleAdress(moradaCompleta);
            if (moradaDto != null && nPorta != null) {
                EnderecoDetailsExtendedDto endereco = moradaService.criarEnderecoSeNecessario(moradaDto);
                if (endereco != null) {
                    setEndereco.accept(dto, enderecoConverter.apply(endereco)); // 👈 conversão segura
                    setNPorta.accept(dto, nPorta);
                }
            }
        }

        return dto;
    }

    private static MoradaSimplesDto extractSimpleAdress(String moradaCompleta) {
        if (moradaCompleta == null || !moradaCompleta.matches(".+,\\s*nº\\d+,\\s*\\d{4}-\\d{3}\\s+.+")) return null;
        try {
            String[] partes = moradaCompleta.split(",");
            String rua = partes[0].trim();
            String porta = partes[1].replaceAll("[^0-9]", "").trim();
            String codLocalidade = partes[2].trim();
            int espaco = codLocalidade.indexOf(' ');
            if (espaco < 0) return null;
            String codigoPostal = codLocalidade.substring(0, espaco).trim();
            String localidade = codLocalidade.substring(espaco + 1).trim();
            MoradaSimplesDto dto = new MoradaSimplesDto();
            dto.setRua(rua);
            dto.setCodigoPostal(codigoPostal);
            dto.setNomeLocalidade(localidade);
            return dto;
        } catch (Exception e) {
            return null;
        }
    }

    public static BigDecimal parseDecimal(String valor) {
        if (valor == null) return null;
        try {
            return new BigDecimal(valor.replace(",", ".").replaceAll("[^0-9.]", ""));
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T copyNonEmptyFields(T origem, T destino, Set<String> camposIgnorar) {
        BeanWrapper origemWrapper = new BeanWrapperImpl(origem);
        BeanWrapper destinoWrapper = new BeanWrapperImpl(destino);

        for (var pd : origemWrapper.getPropertyDescriptors()) {
            String nome = pd.getName();

            if (camposIgnorar.contains(nome) || nome.equals("class")) continue;

            Object valor = origemWrapper.getPropertyValue(nome);
            if (valor instanceof String s && s.isBlank()) continue;
            if (valor != null) {
                destinoWrapper.setPropertyValue(nome, valor);
            }
        }

        return destino;
    }

    public static String label(Enum<?> e) {
        return (e instanceof com.example.cper_core.enums.Interface.EnumWithId)
                ? ((com.example.cper_core.enums.Interface.EnumWithId<?>) e).getLabel()
                : (e != null ? e.name() : "N/A");
    }

}