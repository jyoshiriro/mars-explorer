package org.jyoshiriro.pocs.marsexplorer.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class Coordinate {

    @PositiveOrZero
    @NotNull
    private int x;

    @PositiveOrZero
    @NotNull
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate getNext(Direction direction) {
        return new Coordinate(x + direction.getIncreaseX(), y + direction.getIncreaseY());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
