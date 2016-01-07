package com.hpe.simpleservice;

import com.hpe.simpleservice.error.ValidationException;
import com.hpe.simpleservice.error.Web409Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author truxall
 *
 */
@Controller
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class NameController {

    @Autowired
    private NameGenerator _generator;

    public NameController() {

    }

    @RequestMapping(value = "/names/{count}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public List<Person> getPersonList(@PathVariable("count") int count) throws ValidationException {

        if (!countValid(count)) {
            throw new Web409Exception("count");
        }

        return _generator.getRandomPersons(count);
    }


    @RequestMapping(value = "/name", method = RequestMethod.GET,  produces = {"application/json"})
    @ResponseBody
    public Person getSingleName() {

        return _generator.getRandomName();
    }

    private boolean countValid(int count) {
        return count > 0 && count < 101;
    }

    @ExceptionHandler(Web409Exception.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody public Map<String,Object> handle409Exception(Web409Exception bre) {
        return createErrorHash(bre.getMessage());
    }

    private Map<String, Object> createErrorHash(String message) {
        Map<String, Object> result = new HashMap<>();
        result.put("message", message);
        return result;
    }
}
