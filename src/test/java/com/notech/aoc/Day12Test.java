package com.notech.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Test {
    @Test
    void shouldGetManhattanDistance1() {
        int result = Day12.getManhattanDistance1("day12-test.txt");

        assertEquals(25, result);
    }

    @Test
    void result1() {
        int result = Day12.getManhattanDistance1("day12-input.txt");

        System.out.println(result);
    }

    @Test
    void shouldGetManhattanDistance2() {
        int result = Day12.getManhattanDistance2("day12-test.txt");

        assertEquals(286, result);
    }

    @Test
    void result2() {
        int result = Day12.getManhattanDistance2("day12-input.txt");

        System.out.println(result);
    }
}