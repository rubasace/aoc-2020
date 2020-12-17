package com.notech.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day13Test {

    @Test
    void shouldFindEarliestMultipliedByDifference() {
        double result = Day13.findEarliestMultipliedByDifference("day13-test.txt");

        assertEquals(295, result);
    }

    @Test
    void result1() {
        double result = Day13.findEarliestMultipliedByDifference("day13-input.txt");

        System.out.println(result);
    }


    //    @Test
    //    void shouldFindEarliestAllBusesDepartAtOffsets() {
    //        double result = Day13.findEarliestAllBusesDepartAtOffsets("day13-test.txt");
    //
    //        assertEquals(1068781, result);
    //    }
    //
    //    @Test
    //    void result2() {
    //        double result = Day13.findEarliestAllBusesDepartAtOffsets("day13-input.txt");
    //
    //        System.out.println(result);
    //    }
}