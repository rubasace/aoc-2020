package com.notech.aoc;

import com.notech.aoc.util.FileUtils;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Day11 {

    protected static final char EMPTY = 'L';
    protected static final char OCCUPIED = '#';
    protected static final char FLOOR = '.';

    public static long countOccupiedSeats1(final String fileName) {
        char[][] seats = FileUtils.getLines(fileName)
                                  .map(String::toCharArray)
                                  .toArray(char[][]::new);

        try {
            while (true) {
                seats = mutate(seats, Day11::mutate1);

            }
        } catch (NotMutableException e) {
            //Nothing to do, we just finished mutating
        }
        return Arrays.stream(seats)
                     .flatMap(row -> IntStream.range(0, row.length).mapToObj(i -> row[i]))
                     .filter(c -> c == OCCUPIED)
                     .count();
    }

    public static long countOccupiedSeats2(final String fileName) {
        char[][] seats = FileUtils.getLines(fileName)
                                  .map(String::toCharArray)
                                  .toArray(char[][]::new);

        try {
            while (true) {
                seats = mutate(seats, Day11::mutate2);
            }
        } catch (NotMutableException e) {
            //Nothing to do, we just finished mutating
        }
        return Arrays.stream(seats)
                     .flatMap(row -> IntStream.range(0, row.length).mapToObj(i -> row[i]))
                     .filter(c -> c == OCCUPIED)
                     .count();
    }

    private static char[][] mutate(final char[][] seats, final MutationStrategy mutationStrategy) throws NotMutableException {
        char[][] newSeats = copy(seats);
        int changes = 0;
        for (int y = 0; y < seats.length; y++) {
            for (int x = 0; x < seats[y].length; x++) {
                if (mutationStrategy.mutate(seats, x, y, newSeats)) {
                    changes++;
                }
            }
        }
        if (changes == 0) {
            throw new NotMutableException();
        }
        return newSeats;
    }

    private static boolean mutate1(final char[][] seats, final int x, final int y, final char[][] newSeats) {
        char seat = seats[y][x];

        switch (seat) {
            case OCCUPIED:
                if (countAdjacents(seats, x, y, OCCUPIED) >= 4) {
                    newSeats[y][x] = EMPTY;
                    return true;
                }
                return false;
            case EMPTY:
                if (countAdjacents(seats, x, y, OCCUPIED) == 0) {
                    newSeats[y][x] = OCCUPIED;
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    private static boolean mutate2(final char[][] seats, final int x, final int y, final char[][] newSeats) {
        char seat = seats[y][x];

        switch (seat) {
            case OCCUPIED:
                if (countFirstSeen(seats, x, y, OCCUPIED) >= 5) {
                    newSeats[y][x] = EMPTY;
                    return true;
                }
                return false;
            case EMPTY:
                if (countFirstSeen(seats, x, y, OCCUPIED) == 0) {
                    newSeats[y][x] = OCCUPIED;
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    private static int countAdjacents(final char[][] seats, final int charX, final int charY, char target) {
        int rows = seats.length;
        int columns = seats[0].length;

        int minY = charY == 0 ? 0 : charY - 1;
        int maxY = charY == rows - 1 ? rows - 1 : charY + 1;
        int minX = charX == 0 ? 0 : charX - 1;
        int maxX = charX == columns - 1 ? columns - 1 : charX + 1;

        int count = 0;
        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {
                if (x == charX && y == charY) {
                    continue;
                }
                if (seats[y][x] == target) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int countFirstSeen(final char[][] seats, final int charX, final int charY, char target) {
        int count = 0;
        for (int incrementX = -1; incrementX <= 1; incrementX++) {
            for (int incrementY = -1; incrementY <= 1; incrementY++) {
                if (incrementX == 0 && incrementY == 0) {
                    continue;
                }
                char first = findFirst(seats, charX, charY, incrementX, incrementY);
                if (first == target) {
                    count++;
                }
            }
        }
        return count;
    }

    private static char findFirst(final char[][] seats, final int charX, final int charY, int incrementX, int incrementY) {
        int rows = seats.length;
        int columns = seats[0].length;

        int y = charY + incrementY;
        int x = charX + incrementX;

        while (y <= rows - 1 && y >= 0 && x <= columns - 1 && x >= 0) {
            char seat = seats[y][x];
            if (seat != FLOOR) {
                return seat;
            }
            x += incrementX;
            y += incrementY;
        }
        return FLOOR;
    }

    private static char[][] copy(final char[][] original) {
        return Arrays.stream(original)
                     .map(char[]::clone).toArray(char[][]::new);
    }

    private static class NotMutableException extends Exception {

    }

    @FunctionalInterface
    private interface MutationStrategy {
        boolean mutate(final char[][] seats, final int x, final int y, final char[][] newSeats);
    }
}
