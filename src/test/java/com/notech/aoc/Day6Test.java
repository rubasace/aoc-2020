package com.notech.aoc;

import com.notech.aoc.util.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day6Test {

    @Test
    void shouldCountAnyonesPerGroup() {
        String file = FileUtils.readFile("day6-test.txt");
        IntStream countPerGroup = Day6.countAnyonesYesQuestions(file);

        assertArrayEquals(new int[]{3, 3, 3, 1, 1}, countPerGroup.toArray());
    }

    @Test
    void shouldSumAnyonesPerGroup() {
        String file = FileUtils.readFile("day6-test.txt");

        int sum = Day6.sumAnyonesPerGroup(file);

        assertEquals(11, sum);
    }

    @Test
    void result1() {
        String file = FileUtils.readFile("day6-input.txt");

        int result = Day6.sumAnyonesPerGroup(file);

        System.out.println(result);
    }

    @Test
    void shouldCountEveryonesPerGroup() {
        String file = FileUtils.readFile("day6-test.txt");
        IntStream countPerGroup = Day6.countEveryonesYesQuestions(file);

        assertArrayEquals(new int[]{3, 0, 1, 1, 1}, countPerGroup.toArray());
    }

    @Test
    void shouldSumEveryonesPerGroup() {
        String file = FileUtils.readFile("day6-test.txt");

        int sum = Day6.sumEveryonesPerGroup(file);

        assertEquals(6, sum);
    }

    @Test
    void result2() {
        String file = FileUtils.readFile("day6-input.txt");

        int result = Day6.sumEveryonesPerGroup(file);

        System.out.println(result);
    }
}