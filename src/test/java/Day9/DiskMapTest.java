package Day9;

import Day7.Operations;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiskMapTest {
    @Test
    void checksumIsCorrectFor12345TestData() {
        //given we initialize operations with test data 12345
        DiskMap diskMap = new DiskMap("src/main/resources/Day9-test.txt");
        //when we count the checksum
        long checksum = diskMap.checksum;
        //then checksum is correct
        assertEquals(60, checksum, "Checksum is equal to the correct value");
    }

    @Test
    void checksumIsCorrectForLongerTestData() {
        //given we initialize operations with longer test data
        DiskMap diskMap = new DiskMap("src/main/resources/Day9-test2.txt");
        //when we count the checksum
        long checksum = diskMap.checksum;
        //then checksum is correct
        assertEquals(1928, checksum, "Checksum is equal to the correct value");
    }
}
