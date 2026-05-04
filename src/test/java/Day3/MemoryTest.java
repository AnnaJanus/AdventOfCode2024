package Day3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MemoryTest {
    @Test
    void allTheMultiplicationsShouldBeAddedCorrectlyForTestData(){
        //given we initialize memory with test data
        Memory memory = new Memory("src/main/resources/Day3-test.txt");
        //when we add up all the multiplications
        int sum = memory.sumAll();
        //then sum is correct
        assertEquals(87, sum, "Sum is not equal to the correct value");
    }

    @Test
    void allTheEnabledMultiplicationsShouldBeAddedCorrectlyForTestData(){
        //given we initialize memory with test data
        Memory memory = new Memory("src/main/resources/Day3-test.txt");
        //when we add up all the enabled multiplications
        int sumEnabled = memory.sumEnabled();
        //then sum is correct
        assertEquals(85, sumEnabled, "Sum is not equal to the correct value");
    }
}
