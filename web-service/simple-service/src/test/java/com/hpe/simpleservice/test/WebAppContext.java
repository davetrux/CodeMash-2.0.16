package com.hpe.simpleservice.test;

import com.hpe.simpleservice.Constants;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * A fake context for unit tests
 *
 * @author truxall
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.hpe.simpleservice.test"})

public class WebAppContext extends WebMvcConfigurerAdapter {

    public String gender() {
        return Constants.FEMALE;
    }
}
