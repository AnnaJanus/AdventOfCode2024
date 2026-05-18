package Day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GuardMap {
    public int startX;
    public int startY;
    public List<Point> obstructionsFromDataFile = new ArrayList<>();
    List<String> lines = new ArrayList<>();

    private static final int LOOP_NUMBER = 5; // If the guard is in the same position for the 5th time - he is in a loop

    int xPosition;
    int yPosition;

    int[][] directions = {
            {0, -1},
            {1, 0},
            {0, 1},
            {-1, 0}
    };

    public record GuardRun(int uniquePositions, boolean loop, List<Point> positions) {}
    public record Point(int x, int y){}

    public GuardMap(String datasource) {
        try {
            lines = Files.readAllLines(Path.of(datasource));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int y = 0; y < lines.size(); y++) {
            for (int x = 0; x < lines.get(y).length(); x++) {
                if (lines.get(y).charAt(x) == '^') {
                    startX = x;
                    startY = y;
                } else if (lines.get(y).charAt(x) == '#') {
                    obstructionsFromDataFile.add(new Point(x, y));
                }
            }
        }
    }

    public int countPossibleObstructions(){
        int counter = 0;
        Set<Point> positions = new HashSet<>(runGuard().positions);
        positions.remove(new Point(startX, startY));
        for(Point point : positions){
            List<Point> obstructions = new ArrayList<>(obstructionsFromDataFile);
            obstructions.add(point);
            if (runGuard(obstructions).loop){
                counter++;
            }
        }
        return counter;
    }

    public GuardRun runGuard(){
        return runGuard(obstructionsFromDataFile);
    }

    public GuardRun runGuard(List<Point> obstructions) {
        List<Point> positions = new ArrayList<>();
        positions.add(new Point(startX, startY));

        xPosition = startX;
        yPosition = startY;

        int option = 0;
        boolean finish = false;
        boolean loop = false;

        while (xPosition >= 0 &&
                yPosition >= 0 &&
                xPosition < lines.get(0).length() &&
                yPosition < lines.size() &&
                !finish &&
                !loop) {

            int[] dir = directions[option % 4];

            while (!loop) {
                int newX = xPosition + dir[0];
                int newY = yPosition + dir[1];
                if (obstructions.contains(new Point(newX, newY))) {
                    break;
                } else if (newX < 0 ||
                        newY < 0 ||
                        newX >= lines.get(0).length() ||
                        newY >= lines.size()) {
                    finish = true;
                    break;
                } else {
                    positions.add(new Point(newX, newY));
                    xPosition = newX;
                    yPosition = newY;
                    long count = positions.stream()
                            .filter(p -> p.equals(new Point(newX, newY)))
                            .count();
                    if (count == LOOP_NUMBER) {
                        loop = true;
                    }
                }
            }
            option++;
        }
        Set<Point> uniquePositions = new HashSet<>(positions);
        return new GuardRun(uniquePositions.size(), loop, positions);
    }
}
