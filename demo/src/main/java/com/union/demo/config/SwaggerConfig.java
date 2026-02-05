package com.union.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//swagger 설정 class
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI(){
        String jwtSchemeName = "JWT";
        return new OpenAPI()
                .info(new Info()
                        .title("UniON API")
                        .description("UniON 캡스톤 API 문서")
                        .version("v1.0.0")

                )
                //JWT 보안 스키마 등록
                .components(new Components()
                        .addSecuritySchemes(jwtSchemeName,
                        new SecurityScheme()
                                .name(jwtSchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"))
                );

    }
}
