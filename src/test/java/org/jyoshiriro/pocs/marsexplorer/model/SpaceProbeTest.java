package org.jyoshiriro.pocs.marsexplorer.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.jyoshiriro.pocs.marsexplorer.exception.BoundaryReachedException;
import org.jyoshiriro.pocs.marsexplorer.exception.PlaneNotDefinedException;

import static org.junit.jupiter.api.Assertions.*;

class SpaceProbeTest {

    Plane plane5x5 = new Plane(5, 5);

    @Test
    void setPlaneShouldThrowExceptionForInvalidCoordinate() {
        int invalidX = 6;
        int invalidY = 6;

        assertThrows(BoundaryReachedException.class,
                () -> new SpaceProbe(plane5x5.getWidth(), invalidY, Direction.N).setPlane(plane5x5));

        assertThrows(BoundaryReachedException.class,
                () -> new SpaceProbe(invalidX, plane5x5.getHeight(), Direction.N).setPlane(plane5x5));

        assertThrows(BoundaryReachedException.class,
                () -> new SpaceProbe(invalidX, invalidY, Direction.N).setPlane(plane5x5));
    }

    @Test
    void moveShouldThrowExceptionForInvalidCoordinate() {
        SpaceProbe spaceProbeLowerLimit = new SpaceProbe(0, 0, Direction.W);
        spaceProbeLowerLimit.setPlane(plane5x5);

        assertThrows(BoundaryReachedException.class, () -> spaceProbeLowerLimit.move(Movement.M));

        spaceProbeLowerLimit.move(Movement.L);
        assertThrows(BoundaryReachedException.class, () -> spaceProbeLowerLimit.move(Movement.M));

        SpaceProbe spaceProbeGreatestLimit = new SpaceProbe(5, 5, Direction.N);
        spaceProbeGreatestLimit.setPlane(plane5x5);

        assertThrows(BoundaryReachedException.class, () -> spaceProbeGreatestLimit.move(Movement.M));

        spaceProbeGreatestLimit.move(Movement.R);
        assertThrows(BoundaryReachedException.class, () -> spaceProbeGreatestLimit.move(Movement.M));
    }

    @Test
    void setPlaneShouldWorkForValidCoordinate() {
        assertDoesNotThrow(() -> new SpaceProbe(plane5x5.getWidth(), plane5x5.getHeight(), Direction.N).setPlane(plane5x5));
        assertDoesNotThrow(() -> new SpaceProbe(plane5x5.getWidth()-1, plane5x5.getHeight(), Direction.N).setPlane(plane5x5));
        assertDoesNotThrow(() -> new SpaceProbe(plane5x5.getWidth(), plane5x5.getHeight()-1, Direction.N).setPlane(plane5x5));
        assertDoesNotThrow(() -> new SpaceProbe(0, 0, Direction.N).setPlane(plane5x5));
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
        for (Direction direction : Direction.values()) {
            SpaceProbe spaceProbe = new SpaceProbe(2, 2, direction);
            spaceProbe.setPlane(plane5x5);

            spaceProbe.move(Movement.R);
            assertEquals(direction.getToTheRight(), spaceProbe.getDirection());
        }
    }

    @Test
    void moveShouldTurnLeftCorrectly() {
        for (Direction direction : Direction.values()) {
            SpaceProbe spaceProbe = new SpaceProbe(2, 2, direction);
            spaceProbe.setPlane(plane5x5);

            spaceProbe.move(Movement.L);
            assertEquals(direction.getToTheLeft(), spaceProbe.getDirection());
        }
    }

    @Test
    void movingShouldKeepDirectionAfterMoving() {
        for (Direction direction : Direction.values()) {
            SpaceProbe spaceProbe = new SpaceProbe(2, 2, direction);
            spaceProbe.setPlane(plane5x5);

            spaceProbe.move(Movement.M);
            assertEquals(direction, spaceProbe.getDirection());
        }
    }

    @Test
    @DisplayName("Space probe starting from 1,2 N on a 5x5 plane after LMLMLMLMM should stop at 1,3 N")
    void movingShouldMoveTakeToTheRightCoordinate_Scenario1() {
            SpaceProbe spaceProbe = new SpaceProbe(1, 2, Direction.N);
            spaceProbe.setPlane(plane5x5);

            spaceProbe.move(Movement.L);
            spaceProbe.move(Movement.M);
            assertEquals(0, spaceProbe.getCoordinateX());
            assertEquals(2, spaceProbe.getCoordinateY());
            assertEquals(Direction.W, spaceProbe.getDirection());

            spaceProbe.move(Movement.L);
            spaceProbe.move(Movement.M);
            assertEquals(0, spaceProbe.getCoordinateX());
            assertEquals(1, spaceProbe.getCoordinateY());
            assertEquals(Direction.S, spaceProbe.getDirection());

            spaceProbe.move(Movement.L);
            spaceProbe.move(Movement.M);
            assertEquals(1, spaceProbe.getCoordinateX());
            assertEquals(1, spaceProbe.getCoordinateY());
            assertEquals(Direction.E, spaceProbe.getDirection());

            spaceProbe.move(Movement.L);
            spaceProbe.move(Movement.M);
            assertEquals(1, spaceProbe.getCoordinateX());
            assertEquals(2, spaceProbe.getCoordinateY());
            assertEquals(Direction.N, spaceProbe.getDirection());

            spaceProbe.move(Movement.M);
            assertEquals(1, spaceProbe.getCoordinateX());
            assertEquals(3, spaceProbe.getCoordinateY());
            assertEquals(Direction.N, spaceProbe.getDirection());
    }

    @Test
    @DisplayName("Space probe starting from 3,3 E on a 5x5 plane after MMRMMRMRRM should stop at 5,1 E")
    void movingShouldMoveTakeToTheRightCoordinate_Scenario2() {
            SpaceProbe spaceProbe = new SpaceProbe(3, 3, Direction.E);
            spaceProbe.setPlane(plane5x5);

            spaceProbe.move(Movement.M);
            assertEquals(4, spaceProbe.getCoordinateX());
            assertEquals(3, spaceProbe.getCoordinateY());
            assertEquals(Direction.E, spaceProbe.getDirection());

            spaceProbe.move(Movement.M);
            assertEquals(5, spaceProbe.getCoordinateX());
            assertEquals(3, spaceProbe.getCoordinateY());
            assertEquals(Direction.E, spaceProbe.getDirection());

            spaceProbe.move(Movement.R);
            spaceProbe.move(Movement.M);
            assertEquals(5, spaceProbe.getCoordinateX());
            assertEquals(2, spaceProbe.getCoordinateY());
            assertEquals(Direction.S, spaceProbe.getDirection());

            spaceProbe.move(Movement.M);
            assertEquals(5, spaceProbe.getCoordinateX());
            assertEquals(1, spaceProbe.getCoordinateY());
            assertEquals(Direction.S, spaceProbe.getDirection());

            spaceProbe.move(Movement.R);
            spaceProbe.move(Movement.M);
            assertEquals(4, spaceProbe.getCoordinateX());
            assertEquals(1, spaceProbe.getCoordinateY());
            assertEquals(Direction.W, spaceProbe.getDirection());

            spaceProbe.move(Movement.R);
            spaceProbe.move(Movement.R);
            spaceProbe.move(Movement.M);
            assertEquals(5, spaceProbe.getCoordinateX());
            assertEquals(1, spaceProbe.getCoordinateY());
            assertEquals(Direction.E, spaceProbe.getDirection());
    }
}