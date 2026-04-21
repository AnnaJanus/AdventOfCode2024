package Day4;

import Day3.Memory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class XmasTest {
    @Test
    void allTheXmasShouldBeFoundForTestData(){
        //given we initialize word search with test data
        WordSearch wordSearch = new WordSearch("src/main/resources/Day4-test.txt");
        //when we find all the XMASes
        int counter = wordSearch.countXmas();
        //then number of Xmas found is correct
        assertEquals(18, counter, "Number of XMAS is not equal to the correct value");
    }
}
