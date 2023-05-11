package med.voll.med.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")
                                        .bearerFormat("JWT")))
                        .info(new Info()
                            .title("Api Voll Med")
                            .description("API da Clinica Voll contendo as funcionalidades de cadastrado exclusao e listagem de medicos, pacientes, agendamento e cancelamento de consultas")
                            .contact(new Contact()
                                .name("Gustavo Braga")
                                .email("gustavo.b.moraes2@gmail.com"))
                        .license(new License()
                            .name("Apache 2.0")
                            .url("http://voll/lincensa")));
                    
    }

}
