package org.jyoshiriro.pocs.marsexplorer.exception;

import org.jyoshiriro.pocs.marsexplorer.model.Plane;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BoundaryReachedException extends RuntimeException {

    private String boudaryMessage;

    public BoundaryReachedException(Plane plane, int positionX, int positionY) {
        boudaryMessage = (positionX >= 0 && positionY >= 0)
                ? String.format(
            "Plane (%d,%d) can't accept position (%d,%d)", plane.getWidth(), plane.getHeight(), positionX, positionY)
                : String.format("X and Y should be 0 or positive. Actual X: %d. Actual Y: %d", positionX, positionY);
    }

    @Override
    public String getMessage() {
        return this.boudaryMessage;
    }
}
