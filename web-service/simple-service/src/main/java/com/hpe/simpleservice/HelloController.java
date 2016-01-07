package com.hpe.simpleservice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Simple web service for testing
 *
 * @author truxall
 */
@Controller
@RequestMapping("/hello")
public class HelloController {

    private static final int LONG_LOOP = 10000000;
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD}, produces = {"text/plain"})
    @ResponseBody
    public String getHello() {

        for(int i=0; i < LONG_LOOP; i++) {
            //Killing time
        }
        return Constants.HELLO_MESSAGE;
    }
}