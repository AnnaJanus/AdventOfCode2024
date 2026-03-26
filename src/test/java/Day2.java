import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day2 {
    public static void main(String[] args) {
        List<String> reports = loadReports("src/test/java/Day2Data.txt");
        int safeReports = 0;

        for(String report: reports){
            if(checkReport(report)){
                safeReports++;
            }
        }

        System.out.println(safeReports);
    }

    private static List<String> loadReports(String filePath) {
        List<String> lines = new ArrayList<>();

        try {
            lines = Files.readAllLines(Path.of(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    private static boolean checkReport(String report) {
        String[] numbersText = report.split(" +");
        int[] numbers = new int[numbersText.length];
        for (int i = 0; i < numbersText.length; i++) {
            numbers[i] = Integer.parseInt(numbersText[i]);
        }

        boolean increasing;
        if (numbers[0] > numbers[1]) {
            increasing = false;
        } else if (numbers[0] < numbers[1]) {
            increasing = true;
        } else {
            return false;
        }

        for (int i = 0; i < numbers.length - 1; i++) {
            int difference;

            if (increasing) {
                difference = numbers[i + 1] - numbers[i];
            } else {
                difference = numbers[i] - numbers[i + 1];
            }
            if (difference < 1 || difference > 3) {
                return false;
            }
        }
        return true;
    }
}
