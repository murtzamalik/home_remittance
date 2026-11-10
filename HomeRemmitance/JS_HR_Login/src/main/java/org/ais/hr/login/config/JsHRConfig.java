package org.ais.hr.login.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

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
public class JsHRConfig {



    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis((Predicate<RequestHandler>) RequestHandlerSelectors.basePackage("org.ais.hr.login.controller"))
                .paths((Predicate<String>) PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "JS HOME REMITTANCE ",
                "REST APIs for JS HOME REMITTANCE ",
                "1.0",
                "",
                new Contact("Murtaza Malik", "www.appinsnap.com", "murtaza.malik@appinsnap.com"),
                "Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
    }



}
