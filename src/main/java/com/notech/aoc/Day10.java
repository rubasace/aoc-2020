package com.notech.aoc;

import com.notech.aoc.util.FileUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day10 {

    protected static final int MAX_DIFFERENCE = 3;

    public static long multiplyDifferencesOf1And3(final String fileName) {

        List<Integer> jolts = getJolts(fileName);

        Map<Integer, Long> differences = IntStream.range(1, jolts.size())
                                                  .mapToObj(i -> jolts.get(i) - jolts.get(i - 1))
                                                  .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        return differences.get(1) * (differences.get(3) + 1);
    }


    public static long countPossibleArrangements(final String fileName) {
        List<Integer> jolts = getJolts(fileName);
        Map<Integer, Long> combinationsCache = new HashMap<>();

        return Day10.possibleCombinations(jolts, 0, combinationsCache);

        //        return IntStream.range(1, jolts.size())
        //                        .mapToLong(i -> Day10.possibleCombinations(jolts, i))
        //                        .peek(e -> System.out.println(e))
        //                        .reduce(1, (a, b) -> a * b);
    }

    //    private static long possibleCombinations(final List<Integer> jolts, final int index) {
    //        return IntStream.rangeClosed(1, MAX_DIFFERENCE)
    //                        .map(e -> e + index)
    //                        .filter(e -> e < jolts.size())
    //                        .map(jolts::get)
    //                        .filter(e -> e <= jolts.get(index) + MAX_DIFFERENCE)
    //                        .count();
    //    }

    private static long possibleCombinations(final List<Integer> jolts, final int index, final Map<Integer, Long> combinationsCache) {
        Long cachedCombinations = combinationsCache.get(index);
        if (cachedCombinations != null) {
            return cachedCombinations;
        }
        long combinations = IntStream.rangeClosed(1, MAX_DIFFERENCE)
                                     .map(i -> i + index)
                                     .filter(i -> i < jolts.size() && jolts.get(i) <= jolts.get(index) + MAX_DIFFERENCE)
                                     .mapToLong(i -> possibleCombinations(jolts, i, combinationsCache))
                                     .sum();
        combinations = combinations != 0 ? combinations : 1;
        combinationsCache.put(index, combinations);
        return combinations;
    }

    private static List<Integer> getJolts(final String fileName) {
        Stream<Integer> source = Stream.of(0);
        Stream<Integer> adapters = FileUtils.getLines(fileName).map(Integer::valueOf);

        List<Integer> jolts = Stream.concat(source, adapters)
                                    .sorted()
                                    .collect(Collectors.toList());

        return jolts;
    }

}
