package com.hpe.simpleservice.error;

/**
 * A base exception for a class of exceptions used in data validation
 *
 * @author truxall
 */
public abstract class ValidationException extends Exception {

    private boolean isValid = false;

    public ValidationException(String message) {
        super(message);
    }

    public boolean isValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }
}