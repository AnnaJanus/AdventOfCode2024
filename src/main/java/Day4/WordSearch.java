package Day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordSearch {
    private List<String> lines = new ArrayList<>();
    private final int rows;
    private final int cols;

    private char[][] charMatrix;
    private final String XMAS = "XMAS";
    private final String SAMX = "SAMX";
    private final Pattern patternXMAS = Pattern.compile(XMAS);
    private final Pattern patternSAMX = Pattern.compile(SAMX);
    private final Set<Character> modelSet = Set.of('M', 'S');

    public WordSearch(String datasource) {
        try {
            lines = Files.readAllLines(Path.of(datasource));
        } catch (IOException e) {
            e.printStackTrace();
        }
        rows = lines.size();
        cols = lines.get(0).length();
    }

    public int countXmas() {
        int counter = 0;
        for (String line : lines) {
            counter += findPatternXMASorSAMX(line);
        }

        for (String line : findVertical()) {
            counter += findPatternXMASorSAMX(line);
        }

        for (String line : findRightDiagonal()) {
            counter += findPatternXMASorSAMX(line);
        }

        for (String line : findLeftDiagonal()) {
            counter += findPatternXMASorSAMX(line);
        }

        return counter;
    }

    private long findPatternXMASorSAMX(String line) {
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

    //--------------- second task ----------------

    private void readDataToMatrix() {
        charMatrix = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            String line = lines.get(i);
            for (int j = 0; j < cols; j++) {
                charMatrix[i][j] = line.charAt(j);
            }
        }
    }

    public int findMAS() {
        readDataToMatrix();
        int counter = 0;
        for (int row = 1; row < charMatrix.length - 1; row++) {
            for (int col = 1; col < charMatrix[0].length - 1; col++) {
                if (charMatrix[row][col] == ('A')) {
                    Set<Character> diagonal1 = new HashSet<>();
                    diagonal1.add(charMatrix[row - 1][col - 1]);
                    diagonal1.add(charMatrix[row + 1][col + 1]);
                    Set<Character> diagonal2 = new HashSet<>();
                    diagonal2.add(charMatrix[row - 1][col + 1]);
                    diagonal2.add(charMatrix[row + 1][col - 1]);

                    if (diagonal1.containsAll(modelSet) && diagonal2.containsAll(modelSet)) {
                        counter++;
                    }
                }
            }
        }
        return counter;
    }
}
