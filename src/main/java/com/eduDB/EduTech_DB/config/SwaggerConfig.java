package com.eduDB.EduTech_DB.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                            .title("API gestión usuarios")
                            .version("1.0.0")
                            .description("Documentación de la API para la gestión de los usuarios en proyecto EdutechSPA."));
    }

}
