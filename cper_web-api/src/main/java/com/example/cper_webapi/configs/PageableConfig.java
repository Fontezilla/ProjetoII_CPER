package com.example.cper_webapi.configs;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;

@Configuration
public class PageableConfig {

    @Bean
    public OperationCustomizer customizePageable() {
        return (operation, handlerMethod) -> {
            for (var parameter : handlerMethod.getMethodParameters()) {
                if (Pageable.class.isAssignableFrom(parameter.getParameterType())) {
                    operation.addParametersItem(new Parameter()
                            .in(ParameterIn.QUERY.toString())
                            .schema(new Schema<Integer>().type("integer").example(0))
                            .name("page")
                            .description("Página (0..N)"));

                    operation.addParametersItem(new Parameter()
                            .in(ParameterIn.QUERY.toString())
                            .schema(new Schema<Integer>().type("integer").example(10))
                            .name("size")
                            .description("Número de elementos por página"));

                    operation.addParametersItem(new Parameter()
                            .in(ParameterIn.QUERY.toString())
                            .schema(new Schema<String>().type("string"))
                            .name("sort")
                            .description("Ordenação: propriedade,asc|desc"));
                }
            }
            return operation;
        };
    }
}
