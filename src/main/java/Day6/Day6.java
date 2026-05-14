package Day6;

import java.nio.file.Path;

public class Day6 {
    public static void main(String[] args) {
        GuardMap guardMap = new GuardMap("src/main/resources/Day6Data.txt");
        System.out.println("Task 1: " + guardMap.runGuard());
    }
}
