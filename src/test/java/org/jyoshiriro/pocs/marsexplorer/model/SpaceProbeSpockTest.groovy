package org.jyoshiriro.pocs.marsexplorer.model

import org.jyoshiriro.pocs.marsexplorer.exception.BoundaryReachedException
import org.jyoshiriro.pocs.marsexplorer.exception.PlaneNotDefinedException
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class SpaceProbeSpockTest extends Specification {

    @Shared
    def plane5x5 = new Plane(5, 5)

    @Unroll
    def 'setPlane(<plane(5,5)>) should throw BoundaryReachedException for invalid coordinate (#x, #y)'() {
        when:
        new SpaceProbe(new Coordinate(x, y), Direction.N).setPlane(plane5x5)

        then:
        thrown(BoundaryReachedException)

        where:
        x                   | y
        6                   | 6
        plane5x5.getWidth() | 6
        6                   | plane5x5.getHeight()
    }

    def 'move() should throw BoundaryReachedException for invalid coordinate'() {
        given:
        def spaceProbeLowerLimit = new SpaceProbe(new Coordinate(0, 0), Direction.W)
        spaceProbeLowerLimit.setPlane(plane5x5)

        and:
        def spaceProbeGreatestLimit = new SpaceProbe(new Coordinate(5, 5), Direction.N)
        spaceProbeGreatestLimit.setPlane(plane5x5)

        when:
        spaceProbeLowerLimit.move(Movement.M)

        then:
        thrown(BoundaryReachedException)

        when:
        spaceProbeLowerLimit.move(Movement.L)
        spaceProbeLowerLimit.move(Movement.M)

        then:
        thrown(BoundaryReachedException)

        when:
        spaceProbeGreatestLimit.move(Movement.M)

        then:
        thrown(BoundaryReachedException)

        when:
        spaceProbeGreatestLimit.move(Movement.R)
        spaceProbeGreatestLimit.move(Movement.M)

        then:
        thrown(BoundaryReachedException)
    }

    @Unroll
    def 'setPlane(<plane(5,5)>) should work for the valid coordinate (#x, #y)'() {
        expect:
        new SpaceProbe(new Coordinate(x, y), Direction.N).setPlane(plane5x5)

        where:
        x                       | y
        0                       | 0
        plane5x5.getWidth()     | plane5x5.getHeight()
        plane5x5.getWidth()-1   | plane5x5.getHeight()
        plane5x5.getWidth()     | plane5x5.getHeight()-1
    }

    @Unroll
    def 'move(#movement) should throw PlaneNotDefinedException if there is no Plane'() {
        when:
        new SpaceProbe(new Coordinate(2, 2), Direction.N).move(movement)

        then:
        thrown(PlaneNotDefinedException)

        where:
        movement << Movement.values()
    }

    @Unroll
    def 'move(#direction) should turn Right correctly to #direction.toTheRight'() {
        given:
        def spaceProbe = new SpaceProbe(new Coordinate(2, 2), direction)
        spaceProbe.setPlane(plane5x5)

        when:
        spaceProbe.move(Movement.R)

        then:
        direction.getToTheRight() == spaceProbe.getDirection()

        where:
        direction << Direction.values()
    }

    @Unroll
    def 'move(#direction) should turn Left correctly to #direction.toTheLeft'() {
        given:
        def spaceProbe = new SpaceProbe(new Coordinate(2, 2), direction)
        spaceProbe.setPlane(plane5x5)

        when:
        spaceProbe.move(Movement.L)

        then:
        direction.getToTheLeft() == spaceProbe.getDirection()

        where:
        direction << Direction.values()
    }

    @Unroll
    def 'move(M) from #direction should keep direction'() {
        given:
        def spaceProbe = new SpaceProbe(new Coordinate(2, 2), direction)
        spaceProbe.setPlane(plane5x5)

        when:
        spaceProbe.move(Movement.M)

        then:
        direction == spaceProbe.getDirection()

        where:
        direction << Direction.values()
    }

    def 'Space probe starting from 1,2 N on a 5x5 plane after LMLMLMLMM should stop at 1,3 N'() {
        given:
        def spaceProbe = new SpaceProbe(new Coordinate(1, 2), Direction.N)
        spaceProbe.setPlane(plane5x5)

        when:
        spaceProbe.move(Movement.L);
        spaceProbe.move(Movement.M);

        then:
        spaceProbe.getCoordinate().getX() == 0
        spaceProbe.getCoordinate().getY() == 2
        spaceProbe.getDirection() == Direction.W

        when:
        spaceProbe.move(Movement.L);
        spaceProbe.move(Movement.M);

        then:
        spaceProbe.getCoordinate().getX() == 0
        spaceProbe.getCoordinate().getY() == 1
        spaceProbe.getDirection() == Direction.S

        when:
        spaceProbe.move(Movement.L);
        spaceProbe.move(Movement.M);

        then:
        spaceProbe.getCoordinate().getX() == 1
        spaceProbe.getCoordinate().getY() == 1
        spaceProbe.getDirection() == Direction.E

        when:
        spaceProbe.move(Movement.L);
        spaceProbe.move(Movement.M);

        then:
        spaceProbe.getCoordinate().getX() == 1
        spaceProbe.getCoordinate().getY() == 2
        spaceProbe.getDirection() == Direction.N

        when:
        spaceProbe.move(Movement.M);

        then:
        spaceProbe.getCoordinate().getX() == 1
        spaceProbe.getCoordinate().getY() == 3
        spaceProbe.getDirection() == Direction.N
    }
    
    def 'Space probe starting from 3,3 E on a 5x5 plane after MMRMMRMRRM should stop at 5,1 E'() {
        given:
        def spaceProbe = new SpaceProbe(new Coordinate(3, 3), Direction.E);
        spaceProbe.setPlane(plane5x5);

        when:
        spaceProbe.move(Movement.M);

        then:
        spaceProbe.getCoordinate().getX() == 4
        spaceProbe.getCoordinate().getY() == 3
        spaceProbe.getDirection() == Direction.E

        when:
        spaceProbe.move(Movement.M)

        then:
        spaceProbe.getCoordinate().getX() == 5
        spaceProbe.getCoordinate().getY() == 3
        spaceProbe.getDirection() == Direction.E

        when:
        spaceProbe.move(Movement.R)
        spaceProbe.move(Movement.M)

        then:
        spaceProbe.getCoordinate().getX() == 5
        spaceProbe.getCoordinate().getY() == 2
        spaceProbe.getDirection() == Direction.S

        when:
        spaceProbe.move(Movement.M)

        then:
        spaceProbe.getCoordinate().getX() == 5
        spaceProbe.getCoordinate().getY() == 1
        spaceProbe.getDirection() == Direction.S

        when:
        spaceProbe.move(Movement.R)
        spaceProbe.move(Movement.M)

        then:
        spaceProbe.getCoordinate().getX() == 4
        spaceProbe.getCoordinate().getY() == 1
        spaceProbe.getDirection() == Direction.W

        when:
        spaceProbe.move(Movement.R)
        spaceProbe.move(Movement.R)
        spaceProbe.move(Movement.M)
        
        then:
        spaceProbe.getCoordinate().getX() == 5
        spaceProbe.getCoordinate().getY() == 1
        spaceProbe.getDirection() == Direction.E
    }

}
