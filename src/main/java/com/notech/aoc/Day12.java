package com.notech.aoc;

import com.notech.aoc.util.FileUtils;

import java.util.List;
import java.util.stream.Collectors;

public class Day12 {

    private static final char NORTH = 'N';
    private static final char SOUTH = 'S';
    private static final char EAST = 'E';
    private static final char WEST = 'W';
    private static final char LEFT = 'L';
    private static final char RIGHT = 'R';
    private static final char FORWARD = 'F';

    private static final char[] DIRECTIONS = {EAST, SOUTH, WEST, NORTH};
    protected static final int ONE_ROTATION_DEGREES = 90;

    public static int getManhattanDistance1(final String fileName) {
        List<String> instructions = FileUtils.getLines(fileName)
                                             .collect(Collectors.toList());

        int north = 0;
        int east = 0;
        int direction = 0;
        for (String instruction : instructions) {
            char action = instruction.charAt(0);
            int value = Integer.parseInt(instruction.substring(1));

            if (action == FORWARD) {
                action = DIRECTIONS[direction];
            }

            switch (action) {
                case LEFT:
                    //  Add 4 to hack the module in case of negative numbers. Quick and dirty, I know :)
                    direction = (4 + direction - (value / ONE_ROTATION_DEGREES)) % 4;
                    break;
                case RIGHT:
                    direction = (4 + direction + (value / ONE_ROTATION_DEGREES)) % 4;
                    continue;
                case EAST:
                    east += value;
                    break;
                case WEST:
                    east -= value;
                    break;
                case NORTH:
                    north += value;
                    break;
                case SOUTH:
                    north -= value;
                    break;
            }
        }

        return Math.abs(east) + Math.abs(north);
    }

    public static int getManhattanDistance2(final String fileName) {
        List<String> instructions = FileUtils.getLines(fileName)
                                             .collect(Collectors.toList());

        int east = 10;
        int north = 1;
        int y = 0;
        int x = 0;

        for (String instruction : instructions) {
            char action = instruction.charAt(0);
            int value = Integer.parseInt(instruction.substring(1));

            switch (action) {
                case FORWARD:
                    x += east * value;
                    y += north * value;
                    break;
                case EAST:
                    east += value;
                    break;
                case WEST:
                    east -= value;
                    break;
                case NORTH:
                    north += value;
                    break;
                case SOUTH:
                    north -= value;
                    break;
                default:
                    int rightMoves;
                    if (action == LEFT) {
                        rightMoves = (4 - value / ONE_ROTATION_DEGREES) % 4;
                    } else {
                        rightMoves = (value / ONE_ROTATION_DEGREES) % 4;
                    }
                    if (rightMoves == 1) {
                        int oldNorth = north;
                        north = -east;
                        east = oldNorth;
                    } else if (rightMoves == 2) {
                        east = -east;
                        north = -north;
                    } else {
                        int oldNorth = north;
                        north = east;
                        east = -oldNorth;
                    }
                    break;
            }
        }

        return Math.abs(x) + Math.abs(y);
    }

}
