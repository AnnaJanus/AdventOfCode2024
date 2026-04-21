package Day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordSearch {
    private List<String> lines = new ArrayList<>();
    private final String XMAS = "XMAS";
    private final String SAMX = "SAMX";
    private final Pattern patternXMAS = Pattern.compile(XMAS);
    private final Pattern patternSAMX = Pattern.compile(SAMX);

    public WordSearch(String datasource) {
        try {
            lines = Files.readAllLines(Path.of(datasource));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int countXmas() {
        int counter = 0;
        for (String line : lines) {
            counter += findPattern(line);
        }

        for (String line : findVertical()) {
            counter += findPattern(line);
        }

        for (String line : findRightDiagonal()) {
            counter += findPattern(line);
        }

        for (String line : findLeftDiagonal()) {
            counter += findPattern(line);
        }

        return counter;
    }

    private long findPattern(String line) {
        Matcher matcher = patternXMAS.matcher(line);
        Matcher matcher1 = patternSAMX.matcher(line);
        return matcher.results().count() + matcher1.results().count();
    }

    private List<String> findVertical() {
        List<String> columns = new ArrayList<>();
        for (int i = 0; i < lines.get(0).length(); i++) {
            StringBuilder columnBuilder = new StringBuilder();
            for (String line : lines) {
                columnBuilder.append(line.charAt(i));
            }
            columns.add(columnBuilder.toString());
        }
        return columns;
    }

    private List<String> findRightDiagonal() {
        List<String> result = new ArrayList<>();
        int rows = lines.size();
        int cols = lines.get(0).length();

        // start z górnej krawędzi
        for (int c = 0; c < cols - 3; c++) {
            int r = 0;
            int col = c;
            StringBuilder sb = new StringBuilder();

            while (r < rows && col < cols) {
                sb.append(lines.get(r).charAt(col));
                r++;
                col++;
            }
            result.add(sb.toString());
        }

        // start z lewej krawędzi (bez [0][0])
        for (int r = 1; r < rows - 3; r++) {
            int row = r;
            int c = 0;
            StringBuilder sb = new StringBuilder();

            while (row < rows && c < cols) {
                sb.append(lines.get(row).charAt(c));
                row++;
                c++;
            }
            result.add(sb.toString());
        }

        return result;
    }

    private List<String> findLeftDiagonal() {
        List<String> result = new ArrayList<>();
        int rows = lines.size();
        int cols = lines.get(0).length();

        // start z górnej krawędzi
        for (int c = 3; c < cols; c++) {
            int r = 0;
            int col = c;
            StringBuilder sb = new StringBuilder();

            while (r < rows && col >= 0) {
                sb.append(lines.get(r).charAt(col));
                r++;
                col--;
            }
            result.add(sb.toString());
        }

        // start z prawej krawędzi (bez [0][cols-1])
        for (int r = 1; r < rows - 3; r++) {
            int row = r;
            int c = cols - 1;
            StringBuilder sb = new StringBuilder();

            while (row < rows && c >= 0) {
                sb.append(lines.get(row).charAt(c));
                row++;
                c--;
            }
            result.add(sb.toString());
        }

        return result;
    }
}
