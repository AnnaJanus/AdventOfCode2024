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
    public List<List<Integer>> obstructions = new ArrayList<>();
    List<String> lines = new ArrayList<>();
    Set<List<Integer>> positions = new HashSet<>();

    int xPosition;
    int yPosition;

    int[][] directions = {
            {0, -1},
            {1, 0},
            {0, 1},
            {-1, 0}
    };

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
                    positions.add(List.of(startX,startY));
                } else if (lines.get(y).charAt(x) == '#') {
                    obstructions.add(List.of(x, y));
                }
            }
        }

        xPosition = startX;
        yPosition = startY;
    }

    public int runGuard() {
        int option = 0;
        boolean finish = false;

        while (xPosition >= 0 &&
                yPosition >= 0 &&
                xPosition < lines.get(0).length() &&
                yPosition < lines.size() && !finish) {

            int[] dir = directions[option % 4];

            while (true) {
                int newX = xPosition + dir[0];
                int newY = yPosition + dir[1];
                if (obstructions.contains(List.of(newX, newY))) {
                    break;
                } else if (newX < 0 ||
                        newY < 0 ||
                        newX >= lines.get(0).length() ||
                        newY >= lines.size()) {
                    finish = true;
                    break;
                } else {
                    positions.add(List.of(newX, newY));
                    xPosition = newX;
                    yPosition = newY;
                }
            }
            option++;
        }
        return positions.size();
    }
}
