package com.notech.aoc;

import com.notech.aoc.util.FileUtils;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day5 {

    protected static final int TOTAL_ROWS = 128;
    protected static final int TOTAL_COLUMNS = 8;
    protected static final int ROW_INFO_CHARACTERS = 7;
    protected static final char FRONT = 'F';
    protected static final char BACK = 'B';
    protected static final char LEFT = 'L';
    protected static final char RIGHT = 'R';

    static Seat parse(final String input) {
        String rowInfo = input.substring(0, ROW_INFO_CHARACTERS);
        int row = toNumber(rowInfo, FRONT, BACK);
        String columnInfo = input.substring(ROW_INFO_CHARACTERS);
        int column = toNumber(columnInfo, LEFT, RIGHT);

        return new Seat(row, column);
    }

    static int highestId(final String file) {
        return FileUtils.executePerLine(file, Day5::parse)
                        .mapToInt(Day5.Seat::getId)
                        .max()
                        .orElse(0);
    }

    static int findIsolatedId(final String file) {
        Set<Integer> existentIds = FileUtils.executePerLine(file, Day5::parse)
                                            .map(Seat::getId)
                                            .collect(Collectors.toSet());
        int initialPotentialId = TOTAL_COLUMNS + 1;
        // Equal to TOTAL_ROWS * 8, but more explicit
        int finalPotentialId = (TOTAL_ROWS - 1) * 8 + TOTAL_COLUMNS;

        Set<Integer> missingIds = IntStream.rangeClosed(initialPotentialId, finalPotentialId)
                                           .boxed()
                                           .filter(e -> !existentIds.contains(e))
                                           .collect(Collectors.toSet());
        return missingIds.stream()
                         .filter(e -> !missingIds.contains(e - 1) && !missingIds.contains(e + 1))
                         .findFirst()
                         .orElse(-1);
    }

    private static int toNumber(final String input, final char zero, final char one) {
        String binary = input.replace(zero, '0').replace(one, '1');
        return Integer.parseInt(binary, 2);
    }

    static class Seat {
        private final int row;
        private final int column;
        private final int id;

        public Seat(final int row, final int column) {
            this.row = row;
            this.column = column;
            this.id = row * 8 + column;
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return column;
        }

        public int getId() {
            return id;
        }

    }
}
