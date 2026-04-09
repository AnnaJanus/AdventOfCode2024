package Day2;

public class Day2 {
    public static void main(String[] args) {
        Reports reports = new Reports("src/main/resources/Day2Data.txt");

        System.out.println("Task 1: " + reports.countSafeReports(false));
        System.out.println("Task 1: " + reports.countSafeReports(true));
    }
}
