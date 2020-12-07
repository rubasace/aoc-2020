package com.notech.aoc;

import com.notech.aoc.util.FileUtils;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day6 {

    static IntStream countAnyonesYesQuestions(final String file) {
        String[] groups = FileUtils.splitByBlankSpace(file);
        return Arrays.stream(groups)
                     .mapToInt(Day6::countDistinctYes);

    }

    public static int sumAnyonesPerGroup(final String file) {
        return Day6.countAnyonesYesQuestions(file)
                   .sum();
    }

    static IntStream countEveryonesYesQuestions(final String file) {
        String[] groups = FileUtils.splitByBlankSpace(file);
        return Arrays.stream(groups)
                     .mapToInt(Day6::countAllYes);

    }

    public static int sumEveryonesPerGroup(final String file) {
        return Day6.countEveryonesYesQuestions(file)
                   .sum();
    }

    private static int countAllYes(final String s) {
        String[] usersAnswers = s.split("\\r?\\n");
        Set<Integer> allAnswers = toSet(usersAnswers[0]);

        for (int i = 1; i < usersAnswers.length && allAnswers.size() > 0; i++) {
            Set<Integer> userAnswers = toSet(usersAnswers[i]);
            allAnswers = allAnswers.stream()
                                   .filter(e -> userAnswers.contains(e))
                                   .collect(Collectors.toSet());
        }

        return allAnswers.size();

    }

    private static Set<Integer> toSet(final String answers) {
        return answers.chars()
                      .boxed()
                      .collect(Collectors.toSet());
    }

    private static int countDistinctYes(final String s) {
        return (int) s.chars()
                      .filter(c -> c >= 'a' && c <= 'z')
                      .distinct()
                      .count();
    }
}
