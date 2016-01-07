package com.hpe.simpleservice.error;

/**
 * An exception for duplicated resources that can't be inserted twice
 *
 * Throws an HTTP 409 error for Spring MVC
 *
 * @author truxall
 */
public class Web409Exception extends ValidationException {
    private static final long serialVersionUID = -3332292356834265371L;

    public Web409Exception(String fieldName){
        super(fieldName + " already exists");
    }
}