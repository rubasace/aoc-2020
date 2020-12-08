package com.notech.aoc;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Day8Test {

    @Test
    void shouldReturnAccumulatorBeforeInfiniteLoop() {

        Day8.InfiniteLoopException infiniteLoopException = assertThrows(Day8.InfiniteLoopException.class, () -> Day8.returnAccumulator("day8-test.txt"));

        assertEquals(5, infiniteLoopException.getAccumulator());
    }


    @Test
    void result1() {

        Day8.InfiniteLoopException infiniteLoopException = assertThrows(Day8.InfiniteLoopException.class, () -> Day8.returnAccumulator("day8-input.txt"));

        System.out.println(infiniteLoopException.getAccumulator());
    }

    @Test
    void shouldFixAndGetAccumulator() throws ParseException, Day8.InfiniteLoopException {
        int accumulator = Day8.fixAndGetAccumulator("day8-test.txt");

        assertEquals(8, accumulator);
    }

    @Test
    void result2() throws ParseException {

        int result = Day8.fixAndGetAccumulator("day8-input.txt");

        System.out.println(result);
    }
}