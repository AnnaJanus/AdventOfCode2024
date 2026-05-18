package Day6;

public class Day6 {
    public static void main(String[] args) {
        GuardMap guardMap = new GuardMap("src/main/resources/Day6Data.txt");
        GuardMap.GuardRun run = guardMap.runGuard();
        System.out.println("Task 1: " + run.uniquePositions());
        System.out.println("Task 2: " + guardMap.countPossibleObstructions());
    }
}
