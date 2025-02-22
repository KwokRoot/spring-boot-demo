package org.kwok.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @description: 对接 Swagger UI 配置。
 * @author: guohao
 * @date: 2022/7/15
 */
@Configuration
public class SpringFoxSwaggerConfig {

    /**
     * 访问路径：http://host:port/swagger-ui/index.html
     * 配置基本信息
     */
    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger Restful API")
                .description("Spring-Boot-Demo 接口文档")
                .termsOfServiceUrl("https://kwok.org")
                .contact(new Contact("Spring-Boot-Demo","https://kwok.org","admin@kwok.org"))
                .version("2.2")
                .build();
    }

    /**
     * 配置文档接口
     * @param apiInfo
     */
    @Bean
    public Docket createRestApi(ApiInfo apiInfo) {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo)
                .groupName("default")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build();
    }

}
