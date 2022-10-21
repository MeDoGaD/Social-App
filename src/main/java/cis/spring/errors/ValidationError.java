package cis.spring.errors;

import java.util.ArrayList;
import java.util.List;

public class ValidationError {
    private String uri;
    private List<String> errors;

    public ValidationError(String uri, List<String> errors) {
        this.uri = uri;
        this.errors = errors;
    }

    public ValidationError() {
        errors=new ArrayList<>();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void addError(String msg)
    {
        this.errors.add(msg);
    }
}
