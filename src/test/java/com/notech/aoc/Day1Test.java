package com.notech.aoc;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day1Test {

    @Test
    void shouldMultiplyOfTwoSum2020() {
        int result = Day1.multiplyTwoSum2020("day1-test.txt");

        assertEquals(514579, result);
    }

    @Test
    void shouldMultiplyOfThreeSum2020() {
        int result = Day1.multiplyThreeSum2020("day1-test.txt");

        assertEquals(241861950, result);
    }


    @Test
    void result1() {
        int result = Day1.multiplyTwoSum2020("day1-input.txt");

        System.out.println(result);;
    }

    @Test
    void result2() {
        int result = Day1.multiplyThreeSum2020("day1-input.txt");

        System.out.println(result);;
    }


}