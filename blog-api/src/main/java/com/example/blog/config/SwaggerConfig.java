package com.example.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author jackyfeng
 * @date
 * @apiNote
 */

@Configuration // 配置类
@EnableSwagger2 // 开启swagger
public class SwaggerConfig{
    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.blog.controller")) // 扫描路径
                .build();
    }

    private ApiInfo apiInfo() {
        // 作者信息
        Contact DEFAULT_CONTACT = new Contact("Jackyfeng", "http://jackyfzh.github.io", "435537581@qq.com");
        return new ApiInfo(
                "Jacky的个人博客",
                "api文档",
                "v1.0",
                "http://localhost:8080",
                DEFAULT_CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }
}
