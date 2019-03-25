package com.wjaronski.tiwprproject1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@SpringBootApplication
@EnableHypermediaSupport(type= {EnableHypermediaSupport.HypermediaType.HAL})
@EnableJpaRepositories("com.wjaronski.tiwprproject1.repository")
public class TiwprProject1Application {

    public static void main(String[] args) {
        SpringApplication.run(TiwprProject1Application.class, args);
    }

}
