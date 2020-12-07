package com.notech.aoc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Day5Test {

    @CsvSource({"BFFFBBFRRR, 70, 7, 567",
                "FFFBBBFRRR, 14, 7, 119",
                "BBFFBBFRLL, 102, 4, 820"})
    @ParameterizedTest
    void shouldFindSeat(final String input, int row, int column, int id) {
        Day5.Seat seat = Day5.parse(input);

        assertAll(
                () -> assertEquals(row, seat.getRow()),
                () -> assertEquals(column, seat.getColumn()),
                () -> assertEquals(id, seat.getId())
        );
    }

    @Test
    void shouldFindHighestId() {
        int result = Day5.highestId("day5-test.txt");

        assertEquals(820, result);
    }

    @Test
    void result1() {
        int result = Day5.highestId("day5-input.txt");

        System.out.println(result);
    }

    @Test
    void result2() {
        int result = Day5.findIsolatedId("day5-input.txt");

        System.out.println(result);
    }
}