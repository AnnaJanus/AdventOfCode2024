package Day7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperationsTest {
    @Test
    void allTheCorrectOperationsAreFoundAndAddedCorrectlyForTwoOperatorsAndTestData(){
        //given we initialize operations with test data
        Operations operations = new Operations("src/main/resources/Day7-test.txt");
        //when we find and add up all the correct operations
        long sum = operations.sumOperations(2);
        //then sum is correct
        assertEquals(3749, sum, "Sum is not equal to the correct value");
    }

    @Test
    void allTheCorrectOperationsAreFoundAndAddedCorrectlyForThreeOperatorsAndTestData(){
        //given we initialize operations with test data
        Operations operations = new Operations("src/main/resources/Day7-test.txt");
        //when we find and add up all the correct operations
        long sum = operations.sumOperations(3);
        //then sum is correct
        assertEquals(11387, sum, "Sum is not equal to the correct value");
    }
}
