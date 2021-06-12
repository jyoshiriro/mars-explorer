package org.jyoshiriro.pocs.marsexplorer.model;

public class SpaceProbe {

    private int x;
    private int y;
    private Direction direction;
    private Plan plan;

    public SpaceProbe(int x, int y, Direction direction, Plan plan) {
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

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                ", direction=" + direction +
                '}';
    }

    public static void main(String[] args) {
        Plan plan = new Plan(5, 5);

        SpaceProbe pos1 = new SpaceProbe(1,2, Direction.N, plan);
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

        SpaceProbe pos2 = new SpaceProbe(3,3, Direction.E, plan);
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
