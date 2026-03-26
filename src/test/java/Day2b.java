import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day2b {
    public static void main(String[] args) {
        List<String> reports = loadReports("src/test/java/Day2Data.txt");
        int safeReports = 0;

        for (String report : reports) {
            if (checkReport(report)) {
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
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < numbersText.length; i++) {
            numbers.add(Integer.parseInt(numbersText[i]));
        }

        // check if numbers are decreasing or increasing
        // if you encounter two repetitions return false
        boolean isIncreasing = false;
        int increasing = 0;
        int decreasing = 0;
        int constant = 0;
        for (int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.get(i) < numbers.get(i + 1)) {
                increasing++;
            } else if (numbers.get(i) > numbers.get(i + 1)) {
                decreasing++;
            } else {
                constant++;
            }

            if (decreasing == 2) {
                break;
            } else if (increasing == 2) {
                isIncreasing = true;
                break;
            } else if (constant == 2) {
                return false;
            }
        }

        return isAcceptable(isIncreasing, numbers);
    }

    private static boolean isAcceptable(boolean increasing, List<Integer> numbers) {
        List<Integer> differences = countDifferences(increasing, numbers);
        long errors = differences.stream().filter(i -> i < 1 || i > 3).count();

        if (errors == 0) {
            return true;
        } else if (errors > 2) {
            return false;
        } else {
            for (int i = 0; i < differences.size(); i++) {
                // if there were 1 or 2 errors, check the differences between numbers in reduced array
                // check twice - for array without a number from index i and i+1
                if (differences.get(i) < 1 || differences.get(i) > 3) {
                    List<Integer> numbersWithOneNumberRemoved = getArrayWithoutNumber(numbers, i);
                    System.out.println(numbersWithOneNumberRemoved);
                    if (countDifferences(increasing, numbersWithOneNumberRemoved).stream().noneMatch(ii -> ii < 1 || ii > 3)) {
                        return true;
                    }
                    if (i + 1 < numbers.size()) {
                        List<Integer> numbersWithOneNumberRemoved2 = getArrayWithoutNumber(numbers, i + 1);
                        System.out.println(numbersWithOneNumberRemoved2);
                        if (countDifferences(increasing, numbersWithOneNumberRemoved2).stream().noneMatch(ii -> ii < 1 || ii > 3)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

    private static List<Integer> countDifferences(boolean increasing, List<Integer> numbers) {
        // this function returns array of differences between numbers
        List<Integer> differences = new ArrayList<>();
        if (increasing) {
            for (int i = 0; i < numbers.size() - 1; i++) {
                differences.add(numbers.get(i + 1) - numbers.get(i));
            }
        } else {
            for (int i = 0; i < numbers.size() - 1; i++) {
                differences.add(numbers.get(i) - numbers.get(i + 1));
            }
        }
        return differences;
    }

    private static List<Integer> getArrayWithoutNumber(List<Integer> array, int index) {
        // this function returns array without a single number (found by index)
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            if (i != index) {
                result.add(array.get(i));
            }
        }
        return result;
    }
}
