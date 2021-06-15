package org.jyoshiriro.pocs.marsexplorer.model

import spock.lang.Specification

import static org.jyoshiriro.pocs.marsexplorer.model.Direction.*

class DirectionSpockTest extends Specification {

    def directions = Direction.values()

    def 'All toTheRight and ToTheLeft should be valid'() {
        when:
        directions.each {
            it.getToTheRight()
            it.getToTheLeft()
        }

        then:
        noExceptionThrown()
    }

    def 'Increase should be only 0, -1 or 1'() {
        when:
        def validNumbers = [-1, 0, 1]

        then:
        verifyAll {
            for (direction in directions) {
                validNumbers.contains(direction.getIncreaseX())
                validNumbers.contains(direction.getIncreaseY())
            }
        }
    }

    def 'All turns to Right should be correct'() {
        when:
        def fromToRight = [(N):E, (E):S, (S):W, (W):N]

        then:
        verifyAll {
            for (direction in directions) {
                def actualRight = direction.getToTheRight()
                def expectedRight = fromToRight[direction]
                "toRight in $direction: $actualRight" == "toRight in $direction: $expectedRight"
            }
        }
    }

    def 'All turns to Left should be correct'() {
        when:
        def fromToLeft = [(N):W, (W):S, (S):E, (E):N]

        then:
        verifyAll {
            for (direction in directions) {
                def actualLeft = direction.getToTheLeft()
                def expectedLeft = fromToLeft[direction]
                "toLeft in $direction: $actualLeft" == "toLeft in $direction: $expectedLeft"
            }
        }
    }
}
