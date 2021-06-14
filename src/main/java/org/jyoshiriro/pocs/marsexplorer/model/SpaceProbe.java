package org.jyoshiriro.pocs.marsexplorer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Hidden;
import org.jyoshiriro.pocs.marsexplorer.exception.BoundaryReachedException;
import org.jyoshiriro.pocs.marsexplorer.exception.PlaneNotDefinedException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Map;
import java.util.function.Consumer;

public class SpaceProbe {

    @PositiveOrZero
    @NotNull
    @JsonProperty("x")
    private int coordinateX;

    @PositiveOrZero
    @NotNull
    @JsonProperty("y")
    private int coordinateY;

    @NotNull
    private Direction direction;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Hidden
    private Plane plane;

    @JsonIgnore
    private final Map<Movement, Consumer<Void>> movementsMap;

    public SpaceProbe() {
        movementsMap = Map.of(
                Movement.R, p -> setDirection(getDirection().getToTheRight()),
                Movement.L, p -> setDirection(getDirection().getToTheLeft()),
                Movement.M, p -> walk()
        );
    }

    private void setDirection(Direction newDirection) {
        this.direction = newDirection;
    }

    public void move(Movement movement) {
        if (plane == null) {
            throw new PlaneNotDefinedException();
        }
        movementsMap.get(movement).accept(null);
    }

    public void setPlane(Plane plane) {
        if (coordinateX > plane.getWidth() || coordinateY > plane.getHeight()) {
            throw new BoundaryReachedException(plane, coordinateX, coordinateY);
        }

        this.plane = plane;
    }

    private void walk() {
        coordinateX += getActualXIncrease();
        coordinateY += getActualYIncreasing();
    }

    private int getActualXIncrease() {
        int increase = direction.getIncreaseX();

        if (increase == 0) {
            return 0;
        } else if (increase < 0) {
            return getActualDecrease(coordinateX, increase);
        } else if (increase + coordinateX <= plane.getWidth()) {
            return increase;
        }

        throw newLocalBoundaryReachedException();
    }

    private int getActualYIncreasing() {
        int increasing = direction.getIncreaseY();

        if (increasing == 0) {
            return 0;
        } else if (increasing < 0) {
            return getActualDecrease(coordinateY, increasing);
        } else if (increasing + coordinateY <= plane.getHeight()) {
            return increasing;
        }

        throw newLocalBoundaryReachedException();
    }

    private int getActualDecrease(int currentValue, int decreasing) {
        if (currentValue == 0 && decreasing < 0) {
            throw newLocalBoundaryReachedException();
        }
        return decreasing;
    }

    private BoundaryReachedException newLocalBoundaryReachedException() {
        return new BoundaryReachedException(
                plane, coordinateX + direction.getIncreaseX(), coordinateY + direction.getIncreaseY());
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public Direction getDirection() {
        return direction;
    }

    public Plane getPlane() {
        return plane;
    }
/*
    @Override
    public String toString() {
        return "{" +
                "x=" + coordinateX +
                ", y=" + coordinateY +
                ", direction=" + direction +
                '}';
    }

    public static void main(String[] args) {
        Plane plane = new Plane(5, 5);

        SpaceProbe pos1 = new SpaceProbe(1,2, Direction.N, plane);
        pos1.move(Movement.L);
        pos1.move(Movement.M);
        pos1.move(Movement.L);
        pos1.move(Movement.M);
        pos1.move(Movement.L);
        pos1.move(Movement.M);
        pos1.move(Movement.L);
        pos1.move(Movement.M);
        pos1.move(Movement.M);
        System.out.println(pos1);

        SpaceProbe pos2 = new SpaceProbe(3,3, Direction.E, plane);
        pos2.move(Movement.M);
        pos2.move(Movement.M);
        pos2.move(Movement.R);
        pos2.move(Movement.M);
        pos2.move(Movement.M);
        pos2.move(Movement.R);
        pos2.move(Movement.M);
        pos2.move(Movement.R);
        pos2.move(Movement.R);
        pos2.move(Movement.M);
        System.out.println(pos2);


        SpaceProbe pos3 = new SpaceProbe(0,5, Direction.W, plane);
        pos3.move(Movement.M);
        System.out.println(pos3);

    }*/
}
