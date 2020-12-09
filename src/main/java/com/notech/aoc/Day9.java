package com.notech.aoc;

import com.notech.aoc.util.FileUtils;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day9 {

    public static Long findFirstInvalid(final String fileName, final int preamble) {
        List<Long> numbers = FileUtils.getLines(fileName)
                                      .map(Long::valueOf)
                                      .collect(Collectors.toList());

        return findFirstInvalid(preamble, numbers);

    }

    public static long findWeakness(final String fileName, final int preamble) {
        List<Long> numbers = FileUtils.getLines(fileName)
                                      .map(Long::valueOf)
                                      .collect(Collectors.toList());

        Long firstInvalid = findFirstInvalid(fileName, preamble);

        List<Long> rangeSumming = findRangeSumming(numbers, firstInvalid);
        return rangeSumming.get(0) + rangeSumming.get(rangeSumming.size() - 1);
    }

    private static List<Long> findRangeSumming(final List<Long> numbers, final Long target) {
        int left = 0;
        int right = 0;
        long sum = numbers.get(0);
        while (left < numbers.size() && right < numbers.size()) {
            if (sum == target) {
                return numbers.subList(left, right + 1)
                              .stream()
                              .sorted()
                              .collect(Collectors.toList());
            }
            if (sum < target) {
                right++;
                sum += numbers.get(right);
            } else {
                left++;
                if (left > right) {
                    right = left;
                    sum = numbers.get(right);
                } else {
                    sum -= numbers.get(left - 1);
                }
            }
        }

        throw new RuntimeException("Not found");
    }


    private static long findFirstInvalid(final int preamble, final List<Long> numbers) {
        return IntStream.range(preamble, numbers.size())
                        .filter(i -> !hasSum(numbers.subList(i - preamble, i), numbers.get(i)))
                        .mapToLong(numbers::get)
                        .findFirst()
                        .orElseThrow();
    }

    private static boolean hasSum(List<Long> numbers, final Long target) {
        HashSet<Long> complementaries = new HashSet<>();
        for (Long number : numbers) {
            if (complementaries.contains(number)) {
                return true;
            } else {
                complementaries.add(target - number);
            }
        }
        return false;
    }


}
