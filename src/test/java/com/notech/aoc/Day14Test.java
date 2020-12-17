package com.notech.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day14Test {

    @Test
    void shouldSumAllValues() {
        long result = Day14.sumAllValues("day14-test.txt");

        assertEquals(165, result);
    }

    @Test
    void result1() {
        long result = Day14.sumAllValues("day14-input.txt");

        System.out.println(result);
    }

    @Test
    void shouldSumAllValuesV2() {
        long result = Day14.sumAllValuesV2("day14-test2.txt");

        assertEquals(208, result);
    }

    @Test
    void result2() {
        long result = Day14.sumAllValuesV2("day14-input.txt");

        System.out.println(result);
        ;
    }
}