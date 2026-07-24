package Day6;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class GuardMap {
    private Point startPoint;
    private final List<Point> obstructionsFromDataFile = new ArrayList<>();

    private static final int LOOP_NUMBER = 5; // If the guard is in the same position for the 5th time - he is in a loop

    private final Point currentPosition = new Point();

    private final Point[] directions = {
            new Point(0, -1),
            new Point(1, 0),
            new Point(0, 1),
            new Point(-1, 0)
    };

    private int width;
    private int height;

    public record GuardRun(int uniquePositions, boolean loop, List<Point> positions) {
    }

    public GuardMap(String datasource) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of(datasource));
            width = lines.get(0).length();
            height = lines.size();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < lines.get(y).length(); x++) {
                if (lines.get(y).charAt(x) == '^') {
                    startPoint = new Point(x, y);
                } else if (lines.get(y).charAt(x) == '#') {
                    obstructionsFromDataFile.add(new Point(x, y));
                }
            }
        }
    }

    public int countPossibleObstructions() {
        int counter = 0;
        Set<Point> positions = new HashSet<>(runGuard().positions);
        positions.remove(new Point((int) startPoint.getX(), (int) startPoint.getY()));
        for (Point point : positions) {
            List<Point> obstructions = new ArrayList<>(obstructionsFromDataFile);
            obstructions.add(point);
            if (runGuard(obstructions).loop) {
                counter++;
            }
        }
        return counter;
    }

    public GuardRun runGuard() {
        return runGuard(obstructionsFromDataFile);
    }

    public GuardRun runGuard(List<Point> obstructions) {
        List<Point> positions = new ArrayList<>();
        positions.add(startPoint);

        currentPosition.setLocation(startPoint.getX(), startPoint.getY());

        int option = 0;
        boolean finish = false;
        boolean loop = false;

        while (isOnMap(currentPosition) &&
                !finish &&
                !loop) {

            Point dir = directions[option % 4];

            while (!loop) {
                Point nextPoint = new Point((int) (currentPosition.getX() + dir.getX()),
                        (int) (currentPosition.getY() + dir.getY()));
                if (obstructions.contains(nextPoint)) {
                    break;
                } else if (!isOnMap(nextPoint)) {
                    finish = true;
                    break;
                } else {
                    loop = takeTheNextStepAndCheckIfYouAreStuck(positions, nextPoint);
                }
            }
            option++;
        }
        Set<Point> uniquePositions = new HashSet<>(positions);
        return new GuardRun(uniquePositions.size(), loop, positions);
    }

    private boolean takeTheNextStepAndCheckIfYouAreStuck(List<Point> positions, Point nextPoint) {
        positions.add(nextPoint);
        currentPosition.setLocation(nextPoint.getX(), nextPoint.getY());
        long count = positions.stream()
                .filter(p -> p.equals(nextPoint))
                .count();
        return count == LOOP_NUMBER;
    }

    private boolean isOnMap(Point p) {
        return p.getX() >= 0 &&
                p.getY() >= 0 &&
                p.getX() < width &&
                p.getY() < height;
    }
}
