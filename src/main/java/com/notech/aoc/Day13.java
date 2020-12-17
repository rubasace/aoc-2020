package com.notech.aoc;

import com.notech.aoc.util.FileUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Day13 {

    public static double findEarliestMultipliedByDifference(final String fileName) {
        List<String> lines = FileUtils.getLines(fileName)
                                      .collect(Collectors.toList());

        String outOfService = "x";

        int target = Integer.parseInt(lines.get(0));

        return Arrays.stream(lines.get(1).split(","))
                     .filter(e -> !outOfService.equals(e))
                     .mapToInt(Integer::parseInt)
                     .mapToObj(id -> nearestBus(id, target))
                     .min(Comparator.comparing(nearestBus -> nearestBus.getNearestTimestamp() - target))
                     .map(nearestBus -> nearestBus.getId() * (nearestBus.getNearestTimestamp() - target))
                     .orElseThrow();

    }

    public static double findEarliestAllBusesDepartAtOffsets(final String fileName) {
        List<String> lines = FileUtils.getLines(fileName)
                                      .collect(Collectors.toList());

        String noRestriction = "x";
        String[] numbers = lines.get(1).split(",");

        Map<Integer, Integer> targetBuses = IntStream.range(0, numbers.length)
                                                     .filter(i -> !noRestriction.equals(numbers[i]))
                                                     .boxed()
                                                     .collect(Collectors.toMap(i -> i, i -> Integer.parseInt(numbers[i])));


        List<Integer> sortedIndexes = targetBuses.keySet().stream()
                                                 .sorted(Comparator.comparing(targetBuses::get))
                                                 .collect(Collectors.toList());

        int maxIdIndex = sortedIndexes.get(sortedIndexes.size() - 1);
        int secondMaxIdIndex = sortedIndexes.get(sortedIndexes.size() - 2);

        double timestampJumps = DoubleStream.iterate(targetBuses.get(maxIdIndex) - maxIdIndex, i -> i += targetBuses.get(maxIdIndex))
                                            .filter(i -> allDepartAtOffset(i, Map.of(secondMaxIdIndex, targetBuses.get(secondMaxIdIndex))))
                                            .findFirst()
                                            .orElseThrow();


        return DoubleStream.iterate(100000000000000L, i -> i += targetBuses.get(maxIdIndex))
                           .filter(i -> allDepartAtOffset(i, targetBuses))
                           .findFirst()
                           .orElseThrow();

        //        return DoubleStream.iterate(targetBuses.get(maxIdIndex) - maxIdIndex, i -> i += targetBuses.get(maxIdIndex))
        //                           .filter(i -> allDepartAtOffset(i, targetBuses))
        //                           .findFirst()
        //                           .orElseThrow();
    }

    private static NearestBus nearestBus(final int id, final double target) {
        double numberOfTrips = Math.ceil(target / id);
        return new NearestBus(id, numberOfTrips * id);
    }


    private static boolean allDepartAtOffset(final double timestamp, final Map<Integer, Integer> ids) {
        return ids.entrySet().stream()
                  .allMatch(entry -> nearestIsAtOffset(entry.getValue(), timestamp, entry.getKey()));

    }

    private static boolean nearestIsAtOffset(final Integer id, final double timestamp, final int offset) {
        NearestBus nearestBus = nearestBus(id, timestamp);
        return nearestBus.getNearestTimestamp() - timestamp == offset;
    }

    private static class NearestBus {
        private final int id;
        private final double nearestTimestamp;

        private NearestBus(final int id, final double nearestTimestamp) {
            this.id = id;
            this.nearestTimestamp = nearestTimestamp;
        }

        public int getId() {
            return id;
        }

        public double getNearestTimestamp() {
            return nearestTimestamp;
        }
    }
}
