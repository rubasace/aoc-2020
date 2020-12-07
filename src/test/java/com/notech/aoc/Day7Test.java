package com.notech.aoc;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day7Test {

    @Test
    void shouldCountCanContainShiny() {
        int count = Day7.countCanContainShiny("day7-test.txt");

        assertEquals(4, count);
    }



    @Test
    void result1() {
        int result = Day7.countCanContainShiny("day7-input.txt");

        System.out.println(result);
    }

    @Test
    void shouldCountBagsInsideShiny() {
        int count = Day7.countInsideShiny("day7-test2.txt");

        assertEquals(126, count);
    }

    @Test
    void result2() {
        int result = Day7.countInsideShiny("day7-input.txt");

        System.out.println(result);
    }
}