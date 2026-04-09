package Day2;

import Day3.Memory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportTest {

    @Test
    void allTheStrictlyMonotonicReportsShouldBeCountCorrectlyForTestData(){
        //given we initialize reports with test data
        Reports reports = new Reports("src/main/resources/Day2-test.txt");
        //when we count all the strictly monotonic reports
        int sum = reports.countSafeReports(false);
        //then sum is correct
        assertEquals(1, sum, "Sum is not equal to the correct value");
    }

    @Test
    void allTheStrictlyMonotonicWithOneLevelTolerationReportsShouldBeCountCorrectlyForTestData(){
        //given we initialize reports with test data
        Reports reports = new Reports("src/main/resources/Day2-test.txt");
        //when we add up all the enabled multiplications
        int sum= reports.countSafeReports(true);
        //then sum is correct
        assertEquals(3, sum, "Sum is not equal to the correct value");
    }
}
