package org.jyoshiriro.pocs.marsexplorer.model;

import javax.validation.constraints.Positive;

public class Plane {

    @Positive
    private int width;

    @Positive
    private int height;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
