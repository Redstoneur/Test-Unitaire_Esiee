package fr.knap.testunitaire_esiee.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up Swagger/OpenAPI documentation.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Creates a custom OpenAPI bean with API metadata.
     *
     * @return an OpenAPI instance with custom information.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Archi Web Service API")
                        .version("1.0")
                        .description("API documentation for Archi Web Service"));
    }
}