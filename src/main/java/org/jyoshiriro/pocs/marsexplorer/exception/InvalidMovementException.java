package org.jyoshiriro.pocs.marsexplorer.exception;

import org.jyoshiriro.pocs.marsexplorer.model.Movement;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidMovementException extends RuntimeException {

    private String invalidMovement;

    public InvalidMovementException(String invalidMovement) {
        this.invalidMovement = invalidMovement;
    }

    @Override
    public String getMessage() {
        return String.format("Movement '%s' is not valid. Valid values: %s",
                invalidMovement, Arrays.asList(Movement.values()));
    }
}
