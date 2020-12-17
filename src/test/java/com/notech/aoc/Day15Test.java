package com.notech.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day15Test {

    @Test
    void shouldFind2020thSpokenNumber() {

        int result = Day15.findNthSpokenNumber(2020, 0, 3, 6);

        assertEquals(436, result);
    }

    @Test
    void result1() {

        int result = Day15.findNthSpokenNumber(2020, 10, 16, 6, 0, 1, 17);

        System.out.println(result);
    }

    @Test
    void result2() {

        int result = Day15.findNthSpokenNumber(30000000, 10, 16, 6, 0, 1, 17);

        System.out.println(result);
    }
}