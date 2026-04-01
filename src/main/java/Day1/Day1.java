package Day1;

import java.nio.file.Path;


public class Day1 {
    public static void main(String[] args) {
        Locations locations = new Locations(Path.of("src/main/resources/Day1Data.txt"));

        System.out.println("Task 1: " + locations.sumDistances());
        System.out.println("Task 2: " + locations.similiarity());
    }


}
