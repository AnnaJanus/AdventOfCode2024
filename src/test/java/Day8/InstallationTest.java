package Day8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InstallationTest {
    @Test
    void allTheAntinodesAreFoundForTestData(){
        //given we initialize operations with test data
        Installation installation = new Installation("src/main/resources/Day8-test.txt");
        //when we find and count all the antinodes
        long counter = installation.antinodes.size();
        //then sum is correct
        assertEquals(14, counter, "number of antinodes is incorrect");
    }

    @Test
    void allTheAntinodesOnAnyGridPositionAreFoundForTestData(){
        //given we initialize operations with test data
        Installation installation = new Installation("src/main/resources/Day8-test.txt");
        //when we find and count all the antinodes from the whole grid
        long counter = installation.antinodesPartTwo.size();
        //then sum is correct
        assertEquals(34, counter, "number of antinodes is incorrect");
    }
}
