package com.notech.aoc;

import com.notech.aoc.util.FileUtils;

import java.util.HashSet;

public class Day1 {

    protected static final int TARGET_SUM = 2020;

    public static int multiplyTwoSum2020(final String file) {
        int[] numbers = FileUtils.getLines(file)
                                 .mapToInt(Integer::valueOf)
                                 .toArray();

        return multiplyPairSumming(numbers, TARGET_SUM, -1);
    }

    private static int multiplyPairSumming(final int[] numbers, final int target, final int ignoring) {
        HashSet<Integer> complementaries = new HashSet<>();
        for (int number : numbers) {
            if (number == ignoring) {
                continue;
            }
            if (complementaries.contains(number)) {
                return number * (target - number);
            } else {
                complementaries.add(target - number);
            }
        }
        return 0;
    }

    public static int multiplyThreeSum2020(final String file) {
        int[] numbers = FileUtils.getLines(file)
                                 .mapToInt(Integer::valueOf)
                                 .toArray();

        for (int number : numbers) {
            int extraNumbersMultiplication = multiplyPairSumming(numbers, TARGET_SUM - number, number);
            if (extraNumbersMultiplication != 0) {
                return number * extraNumbersMultiplication;
            }
        }
        return 0;
    }
}
