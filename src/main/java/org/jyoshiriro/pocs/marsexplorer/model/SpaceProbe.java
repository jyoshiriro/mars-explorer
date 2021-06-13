package org.jyoshiriro.pocs.marsexplorer.model;

import org.jyoshiriro.pocs.marsexplorer.exception.BoundaryReachedException;

import java.util.Map;
import java.util.function.Consumer;

public class SpaceProbe {

    private int positionX;
    private int positionY;
    private Direction direction;
    private Plane plane;

    private Map<Movement, Consumer<Void>> movementsMap;

    public SpaceProbe(int positionX, int positionY, Direction direction, Plane plane) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.direction = direction;
        this.plane = plane;

        this.movementsMap = Map.of(
                Movement.R, p -> setDirection(getDirection().getToTheRight()),
                Movement.L, p -> setDirection(getDirection().getToTheLeft()),
                Movement.M, p -> walk()
        );
    }

    private void setDirection(Direction newDirection) {
        this.direction = newDirection;
    }

    public void move(Movement movement) {
        this.movementsMap.get(movement).accept(null);
    }

    private boolean walk() {
        int formerX = this.positionX;
        int formerY = this.positionY;

        this.positionX += getActualXIncrease();
        this.positionY += getActualYIncreasing();

        return formerX != this.positionX || formerY != this.positionY;
    }

    public int getActualXIncrease() {
        int increase = this.direction.getIncreaseX();
        if (increase == 0) {
            return 0;
        } else if (increase < 0) {
            return getActualDecrease(this.positionX, increase);
        } else if (increase + this.positionX <= this.plane.getWidth()) {
            return increase;
        }

        throw newLocalBoundaryReachedException();
    }

    public int getActualYIncreasing() {
        int increasing = this.direction.getIncreaseY();
        if (increasing == 0) {
            return 0;
        } else if (increasing < 0) {
            return getActualDecrease(this.positionY, increasing);
        } else if (increasing + this.positionY <= this.plane.getHeight()) {
            return increasing;
        }

        throw newLocalBoundaryReachedException();
    }

    private int getActualDecrease(int currentValue, int decreasing) {
        if (decreasing < 0 && currentValue == 0) {
            throw newLocalBoundaryReachedException();
        }
        return decreasing;
    }

    private BoundaryReachedException newLocalBoundaryReachedException() {
        return new BoundaryReachedException(
                this.plane, this.positionX + this.direction.getIncreaseX(), this.positionY+this.direction.getIncreaseY());
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public Direction getDirection() {
        return direction;
    }

    public Plane getPlane() {
        return plane;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + positionX +
                ", y=" + positionY +
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

    }
}
