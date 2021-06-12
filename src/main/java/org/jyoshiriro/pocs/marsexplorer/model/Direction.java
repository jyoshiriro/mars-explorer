package org.jyoshiriro.pocs.marsexplorer.model;

public enum Direction {

    N("North", "E", "W", 0, 1),
    S("South", "W", "E", 0, -1),
    W("West", "N", "S", -1, 0),
    E("East", "S", "N", 1, 0);

    private final String description;
    private final String toTheRight;
    private final String toTheLeft;
    private final int increaseX;
    private final int increaseY;

    Direction(String description, String toTheRight, String toTheLeft, int increaseX, int increaseY) {
        this.description = description;
        this.toTheRight = toTheRight;
        this.toTheLeft = toTheLeft;
        this.increaseX = increaseX;
        this.increaseY = increaseY;
    }

    public String getDescription() {
        return description;
    }

    public Direction getToTheRight() {
        return valueOf(toTheRight);
    }

    public Direction getToTheLeft() {
        return valueOf(toTheLeft);
    }

    public int getIncreaseX() {
        return increaseX;
    }

    public int getIncreaseY() {
        return increaseY;
    }
}
