package org.jyoshiriro.pocs.marsexplorer.exception;

import org.jyoshiriro.pocs.marsexplorer.model.Plane;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BoundaryReachedException extends RuntimeException {

    private String boudaryMessage;

    public BoundaryReachedException(Plane plane, int intendedX, int intendedY) {
        boudaryMessage = (intendedX >= 0 && intendedY >= 0)
                ? String.format("Plane '%d x %d' can't accept the coordinate (%d,%d)",
                                plane.getWidth(), plane.getHeight(), intendedX, intendedY)
                : String.format("X and Y should be 0 or positive. Actual X: %d. Actual Y: %d", intendedX, intendedY);
    }

    @Override
    public String getMessage() {
        return this.boudaryMessage;
    }
}
