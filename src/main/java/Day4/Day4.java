package Day4;

public class Day4 {
    public static void main(String[] args) {
        // Test Data
        WordSearch wordSearch = new WordSearch("src/main/resources/Day4-test.txt");
        System.out.println("Ex1: " + wordSearch.countXmas());
        System.out.println("Ex2: " + wordSearch.findMAS());


        // Final data
        WordSearch wordSearch2 = new WordSearch("src/main/resources/Day4Data.txt");
        System.out.println("Ex1: " + wordSearch2.countXmas());
        System.out.println("Ex2: " + wordSearch2.findMAS());

    }
}
