import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day1b {
    private static final List<Integer> leftColumn = new ArrayList<>();
    private static final List<Integer> rightColumn = new ArrayList<>();

    public static void main(String[] args) {
        loadAndSortLocations("src/test/java/Day1Data.txt");
        System.out.println(countSimilarity());
    }

    private static void loadAndSortLocations(String filePath) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line : lines) {
            leftColumn.add(Integer.valueOf(line.split(" +")[0]));
            rightColumn.add(Integer.valueOf(line.split(" +")[1]));
        }

        Collections.sort(leftColumn);
        Collections.sort(rightColumn);
    }

    private static int countSimilarity() {
        Set<Integer> leftColumnWithoutDuplicates = new HashSet<>(leftColumn);
        int similarity = 0;
        for (int leftNumber : leftColumnWithoutDuplicates) {
            int counter = 0;
            for (int rightNumber : rightColumn) {
                if (leftNumber == rightNumber) {
                    counter++;
                } else if (leftNumber < rightNumber) {
                    break;
                }
            }
            similarity += counter * leftNumber;
        }
        return similarity;
    }
}
