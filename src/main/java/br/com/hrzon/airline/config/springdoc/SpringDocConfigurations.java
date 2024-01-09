package br.com.hrzon.airline.config.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                .addSecuritySchemes("bearer-key",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("AirLine - Sistema de Compra de Passagens")
                        .description("Sistema de empresa aérea compra de passagens e bagagens")
                        .contact(new Contact()
                                .name("Adriano Azevedo")
                                .email("adr_ba@yahoo.com.br"))
                        .license(new License()
                                .name("MIT")
                                .url("https://github.com/adrianoazevedo/airline-new")));
    }


}
