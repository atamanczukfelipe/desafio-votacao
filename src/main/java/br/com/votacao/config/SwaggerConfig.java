package br.com.votacao.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                          .title("API de Votação Cooperativa")
                          .version("v1")
                          .description("Documentação da API para gerenciamento de pautas e sessões de votação"));
    }
    
}
