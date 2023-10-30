package com.akashreddy.myfancypdfinvoices.context;

import com.akashreddy.ApplicationLauncher;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = ApplicationLauncher.class)
//@ComponentScan(basePackages = "com.akashreddy.myfancypdfinvoices")
public class MyFancyPdfInvoicesApplicationConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
