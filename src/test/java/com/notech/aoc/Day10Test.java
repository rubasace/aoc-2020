package com.notech.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day10Test {

    @Test
    void shouldMultiplyDifferencesOf1And3() {

        long result = Day10.multiplyDifferencesOf1And3("day10-test.txt");

        assertEquals(220L, result);
    }

    @Test
    void result1() {

        long result = Day10.multiplyDifferencesOf1And3("day10-input.txt");

        System.out.println(result);
    }

    @Test
    void shouldCountPossibleArrangements() {

        long result = Day10.countPossibleArrangements("day10-test.txt");

        assertEquals(19208, result);
    }

    @Test
    void result2() {

        long result = Day10.countPossibleArrangements("day10-input.txt");

        System.out.println(result);
    }
}