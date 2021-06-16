package org.jyoshiriro.pocs.marsexplorer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class MarsExplorerEntityNotFoundException extends RuntimeException {

    public MarsExplorerEntityNotFoundException(String message) {
        super(message);
    }
}
