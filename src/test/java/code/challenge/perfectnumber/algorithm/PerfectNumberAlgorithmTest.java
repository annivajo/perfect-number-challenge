package code.challenge.perfectnumber.algorithm;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PerfectNumberAlgorithmTest {

    @Test
    void isPerfectNumber() {
        int number = 28;
        assertTrue(PerfectNumberAlgorithm.isPerfect(number));
    }

    @Test
    void isNotPerfectNumber() {
        int number = 17;
        assertFalse(PerfectNumberAlgorithm.isPerfect(number));
    }

    @Test
    void findsPerfectNumbersInRange() {
        int start = 1;
        int end = 100;
        List<Integer> perfectNumbers = Arrays.asList(6,28);
        List<Integer> result = PerfectNumberAlgorithm.findAllPerfectNumbersInRange(start, end);

        assertEquals(perfectNumbers.size(), result.size());
        assertEquals(perfectNumbers.get(0), result.get(0));
        assertEquals(perfectNumbers.get(1), result.get(1));

    }

    @Test
    void shouldReturnEmptyListWhenPerfectNumberAreNotFoundInRange(){
        int start = 29;
        int end = 100;
        List<Integer> resultList = PerfectNumberAlgorithm.findAllPerfectNumbersInRange(start, end);

        assertTrue(resultList.isEmpty());
    }
}