package Day8;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;

public class Installation {
    private int width;
    private int height;
    private final List<Antenna> antennas = new ArrayList<>();
    private final Map<Character, List<Antenna>> segregatedAntennas = new HashMap<>();
    Set<Point> antinodes = new HashSet<>();

    public Installation(String datasource) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of(datasource));
            width = lines.get(0).length();
            height = lines.size();
        } catch (IOException e) {
            e.printStackTrace();
        }

        findAntennas(lines);
        segregateAntennas();

        for (Character c : segregatedAntennas.keySet()) {
            findAntinodes(segregatedAntennas.get(c));
        }
    }

    private void findAntennas(List<String> lines) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < lines.get(y).length(); x++) {
                char sign = lines.get(y).charAt(x);
                if (sign != '.') {
                    antennas.add(new Antenna(new Point(x, y), sign));
                }
            }
        }
    }

    private void segregateAntennas() {
        for (Antenna antenna : antennas) {
            char type = antenna.getType();
            if (segregatedAntennas.containsKey(type)) {
                segregatedAntennas.get(type).add(antenna);
            } else {
                segregatedAntennas.put(type, new ArrayList<>(List.of(antenna)));
            }
        }
    }

    private void findAntinodes(List<Antenna> oneTypeAntennas) {
        for (int i = 0; i < oneTypeAntennas.size(); i++) {
            for (int j = 0; j < i; j++) {
                List<Point> antinodesHelper = oneTypeAntennas.get(i).findAntinodes(oneTypeAntennas.get(j));
                for (Point point : antinodesHelper) {
                    if(point.x >= 0 && point.x < width && point.y >= 0 && point.y < height){
                        antinodes.add(point);
                    }
                }
            }
        }
    }
}