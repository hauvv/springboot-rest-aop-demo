package com.ascend.example.demospringboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Autowired
  private ApplicationContext context;

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .enable(context.getEnvironment().getProperty("spring.swagger.enabled").equals("true"))
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.ascend.example"))
        .paths(PathSelectors.any())
        .build();
  }
}
