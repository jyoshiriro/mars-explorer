package org.jyoshiriro.pocs.marsexplorer.model;

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

        this.positionX += getActualXIncreasing();
        this.positionY += getActualYIncreasing();

        return formerX != this.positionX || formerY != this.positionY;
    }

    public int getActualXIncreasing() {
        int increasing = this.direction.getIncreaseX();
        if (increasing > 0) {
            return (increasing + this.positionX <= this.plane.getWidth()) ? increasing : 0;
        }
        return getActualDecreasing(this.positionX, increasing);
    }

    public int getActualYIncreasing() {
        int increasing = this.direction.getIncreaseY();
        if (increasing > 0) {
            return (increasing + this.positionY <= this.plane.getHeight()) ? increasing : 0;
        }
        return getActualDecreasing(this.positionY, increasing);
    }

    private int getActualDecreasing(int currentValue, int decreasing) {
        if (decreasing < 0 && currentValue == 0) {
            return 0;
        }
        return decreasing;
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
    }
}
