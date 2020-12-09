package com.notech.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day9Test {

    @Test
    void shouldFindFirstInvalid() {
        long firstNotValid = Day9.findFirstInvalid("day9-test.txt", 5);

        assertEquals(127L, firstNotValid);
    }

    @Test
    void result1() {
        long result = Day9.findFirstInvalid("day9-input.txt", 25);

        System.out.println(result);
    }

    @Test
    void shouldFindWeakness() {
        long weakness = Day9.findWeakness("day9-test.txt", 5);

        assertEquals(62L, weakness);
    }

    @Test
    void result2() {
        long result = Day9.findWeakness("day9-input.txt", 25);

        System.out.println(result);
    }
}