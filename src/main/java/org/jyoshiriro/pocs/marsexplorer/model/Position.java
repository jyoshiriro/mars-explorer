package org.jyoshiriro.pocs.marsexplorer.model;

public class Position {

    private int x;
    private int y;
    private Direction direction;
    private Plan plan;

    public Position(int x, int y, Direction direction, Plan plan) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.plan = plan;
    }

    public boolean move(Movement movement) {
        switch (movement) {
            case R:
                direction = direction.getToTheRight();
                return true;
            case L:
                direction = direction.getToTheLeft();
                return true;
            default:
                return walk();
        }
    }

    private boolean walk() {
        int formerX = this.x;
        int formerY = this.y;

        this.x += getActualXIncreasing();
        this.y += getActualYIncreasing();

        return formerX != this.x || formerY != this.y;
    }

    public int getActualXIncreasing() {
        int increasing = this.direction.getIncreaseX();
        if (increasing > 0) {
            return (increasing + this.x <= this.plan.getWidth()) ? increasing : 0;
        }
        return getActualDecreasing(this.x, increasing);
    }

    public int getActualYIncreasing() {
        int increasing = this.direction.getIncreaseY();
        if (increasing > 0) {
            return (increasing + this.y <= this.plan.getHeight()) ? increasing : 0;
        }
        return getActualDecreasing(this.y, increasing);
    }

    private int getActualDecreasing(int currentValue, int decreasing) {
        if (decreasing < 0 && currentValue == 0) {
            return 0;
        }
        return decreasing;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public Plan getPlan() {
        return plan;
    }

    public static void main(String[] args) {
        Position p = new Position(5,5, Direction.N, new Plan(5,5));

        p.move(Movement.R);
        p.move(Movement.R);
        p.move(Movement.R);
        p.move(Movement.R);

        p.move(Movement.L);
        p.move(Movement.L);
        p.move(Movement.L);
        p.move(Movement.L);

        p.move(Movement.M);
        p.move(Movement.M);
        p.move(Movement.M);

        p.move(Movement.R);

        p.move(Movement.M);
        p.move(Movement.M);

        p.move(Movement.R);

        p.move(Movement.M);
        p.move(Movement.M);
        p.move(Movement.M);
        p.move(Movement.M);
        p.move(Movement.M);
        p.move(Movement.M);
        p.move(Movement.M);

        p.move(Movement.R);

        p.move(Movement.M);
        p.move(Movement.M);
        p.move(Movement.M);
        p.move(Movement.M);
        p.move(Movement.M);
        p.move(Movement.M);
        p.move(Movement.M);
    }
}
