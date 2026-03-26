import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1 {
    public static void main(String[] args) {
        System.out.println(sumDistance("src/test/java/Day1Data.txt"));
    }

    private static int sumDistance(String filePath) {
        List<String> lines = new ArrayList<>();
        List<Integer> leftColumn = new ArrayList<>();
        List<Integer> rightColumn = new ArrayList<>();

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

        int sum = 0;
        for (int i = 0; i < leftColumn.size(); i++) {
            sum += Math.abs(leftColumn.get(i) - rightColumn.get(i));
        }
        return sum;
    }
}
