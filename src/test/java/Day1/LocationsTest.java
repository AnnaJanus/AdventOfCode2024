package Day1;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class LocationsTest {
    @Test
    void locationShouldCountCorrectlyForTestData() {
        //given we initialize with test data
        Locations locations = new Locations(Path.of("src/main/resources/Day1-test.txt"));
        //when we count the sum of distances
        int distancesSum = locations.sumDistances();
        //then sum is as presented in AoC example
        assertEquals(11,distancesSum,"Distances are not equal");
    }

    @Test
    void locationShouldReturnSimiliarityScoreForTestData() {
        //given we initialize with test data
        Locations locations = new Locations(Path.of("src/main/resources/Day1-test.txt"));
        //when we count similiarity score
        int score = locations.similiarity();
        //then the score is as presented in example
        assertEquals(31,score,"Similiarity scores are different");
    }
}