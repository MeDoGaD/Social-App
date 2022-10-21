package cis.spring.errors;

import org.springframework.http.HttpStatus;

public class NotFoundException extends APIBaseController{

    public NotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return  HttpStatus.NOT_FOUND;
    }
}
