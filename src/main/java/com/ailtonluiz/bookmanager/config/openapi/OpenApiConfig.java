package com.ailtonluiz.bookmanager.config.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestión de Libros")
                        .version("1.0.0")
                        .description("API RESTful para la gestión de libros, autores, géneros y nacionalidades. Ideal para sistemas de bibliotecas, editoriales o catálogos literarios.")
                        .termsOfService("https://github.com/ailtonluiz/book-management-api")
                        .contact(new Contact()
                                .name("Ailton Luiz")
                                .email("contato@ailtonluiz.com")
                                .url("https://ailtonluiz.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                );
    }
}

