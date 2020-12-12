package com.notech.aoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day11Test {

    @Test
    void shouldCountOccupiedSeats1() {

        long seats = Day11.countOccupiedSeats1("day11-test.txt");

        assertEquals(37, seats);
    }

    @Test
    void result1() {

        long seats = Day11.countOccupiedSeats1("day11-input.txt");

        System.out.println(seats);
    }

    @Test
    void shouldCountOccupiedSeats2() {

        long seats = Day11.countOccupiedSeats2("day11-test.txt");

        assertEquals(26, seats);
    }

    @Test
    void result2() {

        long seats = Day11.countOccupiedSeats2("day11-input.txt");

        System.out.println(seats);
    }
}