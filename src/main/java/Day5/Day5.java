package Day5;

import java.nio.file.Path;


public class Day5 {
    public static void main(String[] args) {
        Updates updates = new Updates(Path.of("src/main/resources/Day5Data.txt"));
        updates.sumAllUpdates();

        System.out.println("Task 1:" + updates.sumCorrect);
        System.out.println("Task 2:" + updates.sumIncorrect);
    }
}
