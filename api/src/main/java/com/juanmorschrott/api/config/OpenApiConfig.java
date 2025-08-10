package com.juanmorschrott.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(title = "Hotel API", version = "v1"),
    servers = {
        @Server(url = "http://localhost:8080", description = "Gateway URL")
    }
)
public class OpenApiConfig {
}
