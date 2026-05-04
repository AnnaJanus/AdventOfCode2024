package Day6;

import java.nio.file.Path;

public class Day6 {
    public static void main(String[] args) {
        GuardMap guardMap = new GuardMap(Path.of("src/main/resources/Day6Data.txt"));
        System.out.println(guardMap.runGuard());
    }
}
