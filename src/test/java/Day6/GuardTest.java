package Day6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GuardTest {
    @Test
    void allTheGuardPositionsShouldBeAddedCorrectlyForTestData(){
        //given we initialize guard map with test data
        GuardMap guardMap = new GuardMap("src/main/resources/Day6-test.txt");
        //when we count all the distinct guard positions
        int positions = guardMap.runGuard();
        //then sum is correct
        assertEquals(41, positions, "Distinct positions are counted incorrectly");
    }
}
