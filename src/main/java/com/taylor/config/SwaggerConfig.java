package com.taylor.config;

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


/**
 * @author Taylor
 * @date 2018/8/3
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket swaggerSpringMvcPlugin() {


        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("二郎金金有限责任公司")
                .description("抽奖小程序")
                .contact(new Contact("Taylor",null,null))
                .version("版本号:1.0")
                .build();
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.taylor"))
                .paths(PathSelectors.regex("/api.*"))
                .build()
                .apiInfo(apiInfo)
                .useDefaultResponseMessages(false);
    }
}
