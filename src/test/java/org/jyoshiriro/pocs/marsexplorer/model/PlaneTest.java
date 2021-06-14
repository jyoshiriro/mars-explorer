package org.jyoshiriro.pocs.marsexplorer.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    @Test
    void dimensionsShoulBeTheSameOfConstrutor() {
        int initialWidth = 10;
        int initialHeight = 20;

        Plane plane = new Plane(initialWidth, initialHeight);

        assertEquals(initialWidth, plane.getWidth());
        assertEquals(initialHeight, plane.getHeight());
    }
}