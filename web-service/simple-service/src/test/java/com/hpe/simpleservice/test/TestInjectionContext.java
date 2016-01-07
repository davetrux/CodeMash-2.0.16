package com.hpe.simpleservice.test;

import com.hpe.simpleservice.NameGenerator;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author truxall
 */
@Configuration
public class TestInjectionContext {

    @Bean
    public NameGenerator getGenerator(){
        return Mockito.mock(NameGenerator.class);
    }
}
