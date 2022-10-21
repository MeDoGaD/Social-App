package cis.spring.errors;

import org.springframework.http.HttpStatus;

public abstract class APIBaseController extends RuntimeException{
    public APIBaseController(String message) {
        super(message);
    }

    public abstract HttpStatus getStatusCode();
}
