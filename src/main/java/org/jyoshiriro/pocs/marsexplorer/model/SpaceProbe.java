package org.jyoshiriro.pocs.marsexplorer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Hidden;
import org.jyoshiriro.pocs.marsexplorer.exception.BoundaryReachedException;
import org.jyoshiriro.pocs.marsexplorer.exception.PlaneNotDefinedException;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.function.Consumer;

public class SpaceProbe {

    @NotNull
    private Coordinate coordinate;

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

    public SpaceProbe(@NotNull Coordinate coordinate,
                      @NotNull Direction direction) {
        this();
        this.coordinate = coordinate;
        this.direction = direction;
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
        if (plane.isNotValidCoordinate(coordinate)) {
            throw new BoundaryReachedException(plane, coordinate);
        }

        this.plane = plane;
    }

    private void walk() {
        Coordinate nextCoordinate = coordinate.getNext(direction);

        if (plane.isNotValidCoordinate(nextCoordinate)) {
            throw new BoundaryReachedException(plane, nextCoordinate);
        }

        coordinate = nextCoordinate;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Direction getDirection() {
        return direction;
    }

    public Plane getPlane() {
        return plane;
    }

}
