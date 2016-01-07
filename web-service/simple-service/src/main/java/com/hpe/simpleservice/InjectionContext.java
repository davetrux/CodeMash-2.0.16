package com.hpe.simpleservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @author truxall
 */
@Configuration
public class InjectionContext {

    private static final String CONTEXT_NAME = "java:comp/env";

    @Bean
    public NameGenerator getGenerator() throws NamingException {
        return new NameGenerator(getGender());
    }

    protected String getGender() throws NamingException {
            Context context = (Context) new InitialContext().lookup(CONTEXT_NAME);
            return (String) context.lookup("gender");
        }

}
