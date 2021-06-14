package org.jyoshiriro.pocs.marsexplorer.service;

import org.jyoshiriro.pocs.marsexplorer.exception.PlaneAlreadyDefinedException;
import org.jyoshiriro.pocs.marsexplorer.exception.PlaneNotDefinedException;
import org.jyoshiriro.pocs.marsexplorer.model.Plane;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlaneService {

    // public static final String PLANE_NOT_FOUND_ERROR = "Plane is not valid";

    private Plane plane;

    public void create(Plane plane) {
        if (this.plane != null) {
            throw new PlaneAlreadyDefinedException();
        }
        this.plane = plane;
    }

    public void update(Plane plane) {
        if (this.plane == null) {
            throw new PlaneNotDefinedException();
        }
        this.plane = plane;
    }

    public boolean isValid(Plane plane) {
        return this.plane.getWidth() == plane.getWidth()
                && this.plane.getHeight() == plane.getHeight();
    }

    public boolean hasPlane() {
        return getPlane().isPresent();
    }

    public Optional<Plane> getPlane() {
        return plane == null ? Optional.empty() : Optional.of(plane);
    }
}
