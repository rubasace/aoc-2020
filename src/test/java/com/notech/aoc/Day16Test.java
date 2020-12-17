package com.notech.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day16Test {

    @Test
    void shouldCalculateErrorRate() {
        long result = Day16.calculateErrorRate("day16-test.txt");

        assertEquals(71, result);
    }

    @Test
    void result1() {
        long result = Day16.calculateErrorRate("day16-input.txt");

        System.out.println(result);
    }
}