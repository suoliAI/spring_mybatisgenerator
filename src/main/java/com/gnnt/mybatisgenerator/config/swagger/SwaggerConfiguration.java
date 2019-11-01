package com.gnnt.mybatisgenerator.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2 //开启Swagger2
@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket docket(ApiInfo apiInfo){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                // 指定包名
                .apis(RequestHandlerSelectors.basePackage("com.gnnt.mybatisgenerator.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    @Bean
    public ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("spring_generator项目利用spring-swagger2构建api文档")
                                    .description("实现简单的restful风格")
                                    .version("V1.0")
                                    .termsOfServiceUrl("no terms of serviceUrl")
                                    .contact("suoli")
                                    .licenseUrl("http://www.baidu.com")
                                    .build();
    }
}
