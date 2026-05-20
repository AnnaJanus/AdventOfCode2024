package Day5;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateTest {
    @Test
    void correctUpdatesShouldBeAddedUpProperly() {
        //given we initialize with test data
        Updates updates = new Updates(Path.of("src/main/resources/Day5-test.txt"));
        //when we sum middle numbers of updates
        updates.sumAllUpdates();
        //and get the sum of all middle numbers from correct updates
        int middleNumbersSum = updates.getSumCorrect();
        //then sum is as presented in AoC example
        assertEquals(143, middleNumbersSum, "Sum is incorrect");
    }

    @Test
    void incorrectUpdatesShouldBeAddedUpProperly() {
        //given we initialize with test data
        Updates updates = new Updates(Path.of("src/main/resources/Day5-test.txt"));
        //when we sum middle numbers of updates
        updates.sumAllUpdates();
        //and get the sum of all middle numbers from incorrect updates
        int middleNumbersSum = updates.getSumIncorrect();
        //then sum is as presented in AoC example
        assertEquals(123, middleNumbersSum, "Sum is incorrect");
    }
}
