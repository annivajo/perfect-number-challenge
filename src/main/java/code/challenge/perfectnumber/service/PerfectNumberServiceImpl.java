package code.challenge.perfectnumber.service;

import code.challenge.perfectnumber.algorithm.PerfectNumberAlgorithm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfectNumberServiceImpl implements PerfectNumberService{
    @Override
    public boolean isNumberPerfect(int number) {
        return PerfectNumberAlgorithm.isPerfect(number);
    }

    @Override
    public List<Integer> findAllPerfectNumbersInRange(int start, int end) {
        return PerfectNumberAlgorithm.findAllPerfectNumbersInRange(start,end);
    }
}
