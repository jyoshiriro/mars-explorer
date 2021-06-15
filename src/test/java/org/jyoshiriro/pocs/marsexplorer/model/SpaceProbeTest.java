package org.jyoshiriro.pocs.marsexplorer.model;

import org.junit.jupiter.api.Test;
import org.jyoshiriro.pocs.marsexplorer.exception.BoundaryReachedException;
import org.jyoshiriro.pocs.marsexplorer.exception.PlaneNotDefinedException;

import static org.junit.jupiter.api.Assertions.*;

class SpaceProbeTest {

    @Test
    void setPlaneShouldThrowExceptionForInvalidCoordinate() {
        Plane plane = new Plane(5, 5);
        int invalidX = 6;
        int invalidY = 6;

        assertThrows(BoundaryReachedException.class,
                () -> new SpaceProbe(plane.getWidth(), invalidY, Direction.N).setPlane(plane));

        assertThrows(BoundaryReachedException.class,
                () -> new SpaceProbe(invalidX, plane.getHeight(), Direction.N).setPlane(plane));

        assertThrows(BoundaryReachedException.class,
                () -> new SpaceProbe(invalidX, invalidY, Direction.N).setPlane(plane));
    }

    @Test
    void setPlaneShouldWorkForInvalidCoordinate() {
        Plane plane = new Plane(5, 5);

        assertDoesNotThrow(() -> new SpaceProbe(plane.getWidth(), plane.getHeight(), Direction.N).setPlane(plane));
        assertDoesNotThrow(() -> new SpaceProbe(plane.getWidth()-1, plane.getHeight(), Direction.N).setPlane(plane));
        assertDoesNotThrow(() -> new SpaceProbe(plane.getWidth(), plane.getHeight()-1, Direction.N).setPlane(plane));
        assertDoesNotThrow(() -> new SpaceProbe(0, 0, Direction.N).setPlane(plane));
    }


    @Test
    void moveShouldThrowExceptionIfNoPlane() {
        for (Movement movement : Movement.values()) {
            assertThrows(PlaneNotDefinedException.class,
                    () -> new SpaceProbe(2, 2, Direction.N).move(movement));
        }
    }


    @Test
    void moveShouldTurnRightCorrectly() {
        Plane plane = new Plane(5, 5);

        for (Direction direction : Direction.values()) {
            SpaceProbe spaceProbe = new SpaceProbe(2, 2, direction);
            spaceProbe.setPlane(plane);

            spaceProbe.move(Movement.R);
            assertEquals(direction.getToTheRight(), spaceProbe.getDirection());
        }
    }

    @Test
    void moveShouldTurnLeftCorrectly() {
        Plane plane = new Plane(5, 5);

        for (Direction direction : Direction.values()) {
            SpaceProbe spaceProbe = new SpaceProbe(2, 2, direction);
            spaceProbe.setPlane(plane);

            spaceProbe.move(Movement.L);
            assertEquals(direction.getToTheLeft(), spaceProbe.getDirection());
        }
    }
}