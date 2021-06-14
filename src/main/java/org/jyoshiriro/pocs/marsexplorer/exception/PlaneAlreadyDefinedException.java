package org.jyoshiriro.pocs.marsexplorer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class PlaneAlreadyDefinedException extends RuntimeException {

    @Override
    public String getMessage() {
        return "The plane has been already been defined";
    }
}
