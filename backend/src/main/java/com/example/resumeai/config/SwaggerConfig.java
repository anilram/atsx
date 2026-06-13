package com.example.resumeai.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Resume AI Evaluation System API")
                .version("1.0.0")
                .description("AI-powered Resume vs Job Description Evaluation System")
                .contact(new Contact()
                    .name("Development Team")
                    .email("dev@example.com")));
    }
}
