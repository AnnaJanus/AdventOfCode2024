package Day4;

public class Day4 {
    public static void main(String[] args) {
        // Test Data
        WordSearch wordSearch = new WordSearch("src/main/resources/Day4-test.txt");
        System.out.println(wordSearch.countXmas());

        // Final data
        WordSearch wordSearch2 = new WordSearch("src/main/resources/Day4Data.txt");
        System.out.println(wordSearch2.countXmas());
    }
}
