package com.notech.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day4Test {

    @Test
    void shouldCheckFieldsNotMissing() {

        int valid = Day4.checkAllFieldsButCidArePresent("day4-test.txt");

        assertEquals(2, valid);
    }

    @Test
    void shouldBeAllValid() {

        int valid = Day4.validateIgnoringCid("day4-valid-passports.txt");

        assertEquals(3, valid);
    }

    @Test
    void shouldBeAllInvalid() {

        int valid = Day4.validateIgnoringCid("day4-invalid-passports.txt");

        assertEquals(0, valid);
    }

    @Test
    void result1() {

        int result = Day4.checkAllFieldsButCidArePresent("day4-input.txt");

        System.out.println(result);
    }

    @Test
    void result2() {

        int result = Day4.validateIgnoringCid("day4-input.txt");

        System.out.println(result);
    }
}