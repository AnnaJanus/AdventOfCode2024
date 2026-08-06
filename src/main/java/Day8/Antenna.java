package Day8;

import java.awt.*;
import java.util.List;

public record Antenna(Point coordinates, char type) {

    public char getType() {
        return type;
    }

    public List<Point> findAntinodes(Antenna antenna) {

        int xDifference = antenna.coordinates.x - coordinates.x;
        int yDifference = antenna.coordinates.y - coordinates.y;

        return List.of(new Point(coordinates.x - xDifference, coordinates.y - yDifference),
                new Point(antenna.coordinates.x + xDifference, antenna.coordinates.y + yDifference));
    }
}