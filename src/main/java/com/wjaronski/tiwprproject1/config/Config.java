package com.wjaronski.tiwprproject1.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Wojciech Jaronski
 */

@Import({
        SwaggerConfig.class
})
@Configuration
public class Config {

}
