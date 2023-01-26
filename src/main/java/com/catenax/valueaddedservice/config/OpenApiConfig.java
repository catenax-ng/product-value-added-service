package com.catenax.valueaddedservice.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class OpenApiConfig {

    @Value("${vas.authentication-url.auth-url}")
    private String authUrl;

    @Value("${vas.authentication-url.token-url}")
    private String tokenUrl;

    @Value("${bearer_token.bearer_schema}")
    private String bearerSchema;

    @Value("${bearer_token.bearer_format}")
    private String bearerFormat;


    @Bean
    public OpenAPI oauth2Auth() {
        final String securitySchemeName = "open_id_scheme";
        final String securitySchemeNameBearer = "bearerAuth";
        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .addSecurityItem(new SecurityRequirement().addList("open_id_scheme", List.of("read", "write")))
                .info(new Info().title("VAS API")
                        .version("1.0")
                        .description("Swagger documentation for the Value Added Services APIs"))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .type(SecurityScheme.Type.OAUTH2)
                                .flows(new OAuthFlows().authorizationCode(
                                        new OAuthFlow().authorizationUrl(authUrl)
                                                .tokenUrl(tokenUrl))))
                        .addSecuritySchemes(securitySchemeNameBearer, new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme(bearerSchema).bearerFormat(bearerFormat)));

    }
}