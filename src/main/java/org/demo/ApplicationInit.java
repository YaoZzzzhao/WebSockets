package org.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"org.demo"})
public class ApplicationInit extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationInit.class,args);
    }
}
