package com.notech.aoc;


import com.notech.aoc.util.FileUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day2Test {

    @CsvSource({"1-3 a: abcde, true",
                "1-3 b: cdefg, false",
                "2-9 c: ccccccccc, true"})
    @ParameterizedTest
    void shouldApplyPolicy1(final String password, final boolean correct) {
        boolean result = Day2.applyPolicy1(password);

        assertEquals(correct, result);
    }

    @CsvSource({"1-3 a: abcde, true",
                "1-3 b: cdefg, false",
                "2-9 c: ccccccccc, false"})
    @ParameterizedTest
    void shouldApplyPolicy2(final String password, final boolean correct) {
        boolean result = Day2.applyPolicy2(password);

        assertEquals(correct, result);
    }

    @Test
    void result1() {
        long correct = FileUtils.executePerLine("day2.txt", Day2::applyPolicy1)
                .filter(e -> e)
                .count();
        System.out.println(correct);
    }

    @Test
    void result2() {
        long correct = FileUtils.executePerLine("day2.txt", Day2::applyPolicy2)
                .filter(e -> e)
                .count();
        System.out.println(correct);
    }

}