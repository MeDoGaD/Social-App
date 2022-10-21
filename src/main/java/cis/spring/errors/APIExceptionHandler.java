package cis.spring.errors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(APIBaseController.class)
    public ResponseEntity<ErrorMessage> handleExceptions(APIBaseController ex,WebRequest webRequest){
        ErrorMessage errorMessage=new ErrorMessage();
        errorMessage.setMessage(ex.getMessage());
        errorMessage.setUri(webRequest.getDescription(false));
        return new ResponseEntity<>(errorMessage,ex.getStatusCode());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ValidationError validationError=new ValidationError();
        validationError.setUri(request.getDescription(false));

        List<FieldError>fieldErrors=ex.getBindingResult().getFieldErrors();
        for(FieldError f : fieldErrors)
        {
            validationError.addError(f.getDefaultMessage());
        }
        return new ResponseEntity<>(validationError,HttpStatus.BAD_REQUEST);
    }
}
