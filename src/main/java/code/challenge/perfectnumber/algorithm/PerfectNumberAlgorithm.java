package code.challenge.perfectnumber.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PerfectNumberAlgorithm {

    private PerfectNumberAlgorithm() {
    }

    public static boolean isPerfect(int number) {
        int sum = 1;

        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                if (i * i != number) {
                    sum = sum + i + number / i;
                } else {
                    sum = sum + i;
                }
            }
        }

        return sum == number && number != 1;
    }

    public static List<Integer> findAllPerfectNumbersInRange(int start, int end) {
        if (start > end) {
            return Collections.emptyList();
        }

        int diff = end - start;
        List<Integer> perfectNumbers = new ArrayList<>();

        for (int i = 0; i < diff; i++) {
            if (isPerfect(start)) {
                perfectNumbers.add(start);
            }
            start++;
        }
        return perfectNumbers;

    }
}
