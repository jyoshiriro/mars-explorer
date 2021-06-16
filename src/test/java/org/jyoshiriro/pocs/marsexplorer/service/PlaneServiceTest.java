package org.jyoshiriro.pocs.marsexplorer.service;

import org.junit.jupiter.api.Test;
import org.jyoshiriro.pocs.marsexplorer.exception.PlaneAlreadyDefinedException;
import org.jyoshiriro.pocs.marsexplorer.exception.PlaneNotDefinedException;
import org.jyoshiriro.pocs.marsexplorer.model.Plane;

import static org.junit.jupiter.api.Assertions.*;

class PlaneServiceTest {

    @Test
    void createShouldRegisterPlaneWorkIfNoPlane() {
        PlaneService service = new PlaneService();
        Plane plane = new Plane(2, 2);

        service.create(plane);

        assertEquals(plane, service.getPlane().get());
        assertTrue(service.hasPlane());
    }

    @Test
    void createShouldThrowPlaneAlreadyDefinedExceptionIfThereIsPlane() {
        PlaneService service = new PlaneService();
        Plane plane = new Plane(2, 2);
        service.create(plane);

        assertThrows(PlaneAlreadyDefinedException.class, () -> service.create(new Plane(20, 20)));
    }


    @Test
    void updateShouldUpdatePlaneWorkIfThereIsPlane() {
        PlaneService service = new PlaneService();
        service.create(new Plane(1, 1));

        Plane plane = new Plane(2, 2);
        service.update(plane);

        assertEquals(plane, service.getPlane().get());
    }


    @Test
    void updateShouldThrowPlaneNotDefinedExceptionIfThereIsNoPlane() {
        PlaneService service = new PlaneService();

        assertThrows(PlaneNotDefinedException.class, () -> service.update(new Plane(20, 20)));
    }

    @Test
    void hasPlaneShouldReturnTrueIfThereIsPlane() {
        PlaneService service = new PlaneService();
        assertFalse(service.hasPlane());

        service.create(new Plane(2, 2));

        assertTrue(service.hasPlane());
    }
}