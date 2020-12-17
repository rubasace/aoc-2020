package com.notech.aoc;

import com.notech.aoc.util.FileUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day16 {

    private static final Pattern RANGE_PATTERN = Pattern.compile("(\\d+)-(\\d+)");
    protected static final String NEARBY_TICKETS_PREFIX = "nearby tickets:";
    protected static final String FIELDS_SEPARATOR = ",";

    public static long calculateErrorRate(final String fileName) {
        String file = FileUtils.readFile(fileName);

        String[] sections = FileUtils.splitByBlankSpace(file);
        Set<Integer> validValues = Arrays.stream(FileUtils.splitByLine(sections[0]))
                                         .flatMap(Day16::valuesInRange)
                                         .collect(Collectors.toSet());
        return Arrays.stream(FileUtils.splitByLine(sections[2]))
                     .filter(e -> !e.startsWith(NEARBY_TICKETS_PREFIX))
                     .flatMap(e -> Arrays.stream(e.split(FIELDS_SEPARATOR).clone()))
                     .mapToInt(Integer::parseInt)
                     .filter(e -> !validValues.contains(e))
                     .sum();
    }

    private static Stream<Integer> valuesInRange(final String line) {
        Matcher matcher = RANGE_PATTERN.matcher(line);
        List<Stream<Integer>> ranges = new ArrayList<>();
        while (matcher.find()) {
            ranges.add(IntStream.rangeClosed(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2))).boxed());
        }

        return ranges.stream()
                     .flatMap(e -> e);

    }
}
