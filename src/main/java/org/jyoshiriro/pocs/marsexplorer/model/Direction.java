package org.jyoshiriro.pocs.marsexplorer.model;


import java.util.Arrays;

public enum Direction {

    N("North", 0, 1),
    S("South", 0, -1),
    W("West", -1, 0),
    E("East", 1, 0);

    private static final Direction[] DIRECTIONS_CLOCK_WISE = {N, E, S, W};

    private final String description;
    private final int increaseX;
    private final int increaseY;

    private Integer currentPosition;
    private Direction toTheRight;
    private Direction toTheLeft;

    Direction(String description, int increaseX, int increaseY) {
        this.description = description;
        this.increaseX = increaseX;
        this.increaseY = increaseY;
    }

    public String getDescription() {
        return description;
    }

    public Direction getToTheRight() {
        if (this.toTheRight == null) {
            createToTheRight();
        }
        return toTheRight;
    }

    public Direction getToTheLeft() {
        if (this.toTheLeft == null) {
            createToTheLeft();
        }
        return toTheLeft;
    }

    private void createToTheRight() {
        int currentPosition = getCurrentPositionAtClockWise();
        int positionForRight = (currentPosition < DIRECTIONS_CLOCK_WISE.length - 1) ? currentPosition + 1 : 0;
        this.toTheRight = DIRECTIONS_CLOCK_WISE[positionForRight];
    }

    private void createToTheLeft() {
        int currentPosition = getCurrentPositionAtClockWise();
        int positionForLeft =  (currentPosition > 0) ? currentPosition - 1 : DIRECTIONS_CLOCK_WISE.length - 1;
        this.toTheLeft = DIRECTIONS_CLOCK_WISE[positionForLeft];
    }
    
    private int getCurrentPositionAtClockWise() {
        if (currentPosition == null) {
            currentPosition = Arrays.asList(DIRECTIONS_CLOCK_WISE).indexOf(this);
        }
        return this.currentPosition;
    }

    public int getIncreaseX() {
        return increaseX;
    }

    public int getIncreaseY() {
        return increaseY;
    }
}
