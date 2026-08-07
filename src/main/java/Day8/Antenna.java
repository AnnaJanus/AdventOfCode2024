package Day8;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public record Antenna(Point coordinates, char type) {

    public char getType() {
        return type;
    }

    public Set<Point> findAntinodes(Antenna antenna) {

        int xDifference = antenna.coordinates.x - coordinates.x;
        int yDifference = antenna.coordinates.y - coordinates.y;

        return Set.of(new Point(coordinates.x - xDifference, coordinates.y - yDifference),
                new Point(antenna.coordinates.x + xDifference, antenna.coordinates.y + yDifference));
    }

    public Set<Point> findAntinodes(Antenna antenna, int width, int height) {

        Set<Point> antinodes = new HashSet<>();
        int xDifference = antenna.coordinates.x - coordinates.x;
        int yDifference = antenna.coordinates.y - coordinates.y;

        int counter = 0;
        while (true) {
            int x = coordinates.x - xDifference * counter;
            int y = coordinates.y - yDifference * counter;
            if (x < 0 || x >= width || y < 0 || y >= height) {
                break;
            }
            antinodes.add(new Point(x, y));
            counter++;
        }

        counter = 0;
        while (true) {
            int x = coordinates.x + xDifference * counter;
            int y = coordinates.y + yDifference * counter;
            if (x < 0 || x >= width || y < 0 || y >= height) {
                break;
            }
            antinodes.add(new Point(x, y));
            counter++;
        }

        return antinodes;
    }
}