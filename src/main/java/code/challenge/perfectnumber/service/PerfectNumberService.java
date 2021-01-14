package code.challenge.perfectnumber.service;

import java.util.List;

public interface PerfectNumberService {

    boolean isNumberPerfect(int number);

    List<Integer> findAllPerfectNumbersInRange(int start, int end);
}
