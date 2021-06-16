package org.jyoshiriro.pocs.marsexplorer.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class Plane {

    @Positive
    @NotNull
    private int width;

    @Positive
    @NotNull
    private int height;

    public Plane(@Positive @NotNull int width, @Positive @NotNull int height) {
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
