package org.jyoshiriro.pocs.marsexplorer.model;

public class Plane {

    private final int width;
    private final int height;

    public Plane(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
