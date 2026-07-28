package Day6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GuardTest {
    @Test
    void allTheGuardPositionsShouldBeAddedCorrectlyForTestData(){
        //given we initialize guard map with test data
        GuardMap guardMap = new GuardMap("src/main/resources/Day6-test.txt");
        //when we count all the distinct guard positions
        GuardMap.GuardRun run = guardMap.runGuard();
        //then sum is correct
        assertEquals(41, run.uniquePositions(), "Distinct positions are counted incorrectly");
    }

    @Test
    void loopShouldBeDetectedCorrectlyForTestData(){
        //given we initialize guard map with test data
        GuardMap guardMap = new GuardMap("src/main/resources/Day6-loopTest.txt");
        //when we run the guard
        GuardMap.GuardRun run = guardMap.runGuard();
        //we get the information about being in a loop
        assertTrue(run.loop(), "Guard isn't in a loop");
    }

    @Test
    void allTheObstructionsPositionsShouldBeAddedCorrectlyForTestData(){
        //given we initialize guard map with test data
        GuardMap guardMap = new GuardMap("src/main/resources/Day6-test.txt");
        //when we count all the possible obstructions positions
        int counter = guardMap.countPossibleObstructions();
        //then sum is correct
        assertEquals(6, counter, "Possible obstruction positions are counted incorrectly");
    }
}
