package org.kwok.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 对接 Swagger UI 配置。
 * @author: Kwok
 * @date: 2025/2/22
 */
@Configuration
public class SpringDocSwaggerConfig {

    /**
     * 访问路径：http://host:port/api/swagger-ui.html
     * 配置基本信息
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Swagger Restful API")
                        .description("Spring-Boot-Demo 接口文档")
                        .contact(new Contact()
                                .name("Spring-Boot-Demo")
                                .url("https://kwok.org")
                                .email("admin@kwok.org"))
                        .version("1.0"));
    }

}
