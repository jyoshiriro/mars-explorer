package org.jyoshiriro.pocs.marsexplorer.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.jyoshiriro.pocs.marsexplorer.model.Direction.*;

class DirectionTest {

    @Test
    void toTheRightToTheLeftAreValid() {
        for (Direction direction : Direction.values()) {
            try {
                direction.getToTheRight();
            } catch (IllegalArgumentException e) {
                fail(String.format("Valor de direita inválido para %s", direction));
            }
            try {
                direction.getToTheLeft();
            } catch (IllegalArgumentException e) {
                fail(String.format("Valor de esquerda inválido para %s", direction));
            }
        }
    }

    @Test
    void increaseOnly0OneNegativeOne() {
        List<Integer> validNumbers = Arrays.asList(-1, 0, 1);

        for (Direction direction : Direction.values()) {
            assertTrue(validNumbers.contains(direction.getIncreaseX()),
                    String.format("Incremento X '%s' não é -1, nem 0 e nem 1 para %s", direction.getIncreaseX(), direction));

            assertTrue(validNumbers.contains(direction.getIncreaseY()),
                    String.format("Incremento Y '%s' não é -1, nem 0 e nem 1 para %s", direction.getIncreaseY(), direction));
        }
    }

    @Test
    void allTurnsToRightAreCorrect() {
        Map<Direction, Direction> fromToRight = Map.of(N,E, E,S, S,W, W,N);

        for (Direction direction : Direction.values()) {
            Direction actualRight = direction.getToTheRight();
            Direction expectedRight = fromToRight.get(direction);

            assertEquals(expectedRight, actualRight);
        }
    }

    @Test
    void allTurnsToLeftAreCorrect() {
        Map<Direction, Direction> fromToLeft = Map.of(N,W, W,S, S,E, E,N);

        for (Direction direction : Direction.values()) {
            Direction actualLeft = direction.getToTheLeft();
            Direction expectedLeft = fromToLeft.get(direction);

            assertEquals(expectedLeft, actualLeft);
        }
    }

}