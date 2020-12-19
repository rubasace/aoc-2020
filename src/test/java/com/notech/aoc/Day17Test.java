package com.notech.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day17Test {

    @Test
    void shouldCountActiveCubes() {
        long result = Day17.countActiveCubes("day17-test.txt", 6, false);

        assertEquals(112, result);
    }

    @Test
    void result1() {
        long result = Day17.countActiveCubes("day17-input.txt", 6, false);

        System.out.println(result);
    }

    @Test
    void shouldCountActiveCubes4D() {
        long result = Day17.countActiveCubes("day17-test.txt", 6, true);

        assertEquals(848, result);
    }

    @Test
    void result2() {
        long result = Day17.countActiveCubes("day17-input.txt", 6, true);

        System.out.println(result);
    }
}