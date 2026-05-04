package Day3;

public class Day3 {
    public static void main(String[] args) {
        Memory memory = new Memory("src/main/resources/Day3Data.txt");

        System.out.println("Task 1: " + memory.sumAll());
        System.out.println("Task 2: " + memory.sumEnabled());
    }
}
