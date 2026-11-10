package org.ais.hr.user_management.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.function.Predicate;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 11/2/2021
 * Time: 12:03 AM
 * Project : jscash
 */

@Configuration
public class JsHrConfig {
    

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis((Predicate<RequestHandler>) RequestHandlerSelectors.basePackage("org.ais.hr.user_management.controller"))
                .paths((Predicate<String>) PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "JS Home Remit USER MANAGEMENT REST FULL SERVICES",
                "JS Home Remit USER MANAGEMENT WEB ",
                "1.0",
                "",
                new Contact("Murtaza Malik", "www.appinsnap.com", "murtaza.malik@appinsnap.com"),
                "Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
    }



}
