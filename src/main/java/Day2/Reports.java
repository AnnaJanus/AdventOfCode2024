package Day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Reports {
    List<String> reports = new ArrayList<>();

    public Reports(String datasource) {
        try {
            reports = Files.readAllLines(Path.of(datasource));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Integer> splitNumbersInReport(String report) {
        String[] numbersText = report.split(" +");
        List<Integer> numbers = new ArrayList<>();
        for (String s : numbersText) {
            numbers.add(Integer.parseInt(s));
        }
        return numbers;
    }

    private boolean isIncreasing(List<Integer> numbers) {
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
        return isIncreasing;
    }

    private boolean isStrictlyMonotonic(boolean increasing, List<Integer> numbers) {
        for (int i = 0; i < numbers.size() - 1; i++) {
            int difference;

            if (increasing) {
                difference = numbers.get(i + 1) - numbers.get(i);
            } else {
                difference = numbers.get(i) - numbers.get(i + 1);
            }
            if (difference < 1 || difference > 3) {
                return false;
            }
        }
        return true;
    }

    public int countSafeReports(boolean tolerateSingleBadLevel) {
        int safeReports = 0;

        for (String report : reports) {
            List<Integer> numbers = splitNumbersInReport(report);
            boolean isIncreasing = isIncreasing(numbers);

            boolean isSafe = tolerateSingleBadLevel ?
                    isAcceptable(isIncreasing, numbers) :
                    isStrictlyMonotonic(isIncreasing, numbers);

            if(isSafe){
                safeReports++;
            }
        }
        return safeReports;
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
                    List<Integer> numbersWithOneNumberRemoved = new ArrayList<>(numbers);
                    numbersWithOneNumberRemoved.remove(i);
                    if (countDifferences(increasing, numbersWithOneNumberRemoved).stream().noneMatch(ii -> ii < 1 || ii > 3)) {
                        return true;
                    }
                    if (i + 1 < numbers.size()) {
                        List<Integer> numbersWithOneNumberRemoved2 = new ArrayList<>(numbers);
                        numbersWithOneNumberRemoved2.remove(i + 1);
                        if (countDifferences(increasing, numbersWithOneNumberRemoved2).stream().noneMatch(ii -> ii < 1 || ii > 3)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }
}
