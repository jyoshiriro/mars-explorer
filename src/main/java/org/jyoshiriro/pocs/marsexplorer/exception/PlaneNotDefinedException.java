package org.jyoshiriro.pocs.marsexplorer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class PlaneNotDefinedException extends RuntimeException {

    @Override
    public String getMessage() {
        return "The plane has not been been defined yet";
    }
}
