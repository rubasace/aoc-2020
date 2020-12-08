package com.notech.aoc;

import com.notech.aoc.util.FileUtils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Day8 {

    private final static String ACCUMULATOR = "acc";
    private final static String JUMP = "jmp";
    private final static String NO_OP = "nop";
    protected static final String LINE_SEPARATOR = "\\r?\\n";
    protected static final String SPACE_SEPARATOR = "\\s+";
    protected static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("+#;-#");

    public static int returnAccumulator(final String input) throws ParseException, InfiniteLoopException {
        String lines[] = FileUtils.readFile(input).split(LINE_SEPARATOR);
        return returnAccumulator(lines);
    }

    private static int returnAccumulator(final String[] lines) throws InfiniteLoopException, ParseException {
        int accumulator = 0;
        int index = 0;
        Set<Integer> visitedIndexes = new HashSet<>();

        while (index < lines.length) {
            if (!visitedIndexes.add(index)) {
                throw new InfiniteLoopException(accumulator);
            }
            String[] lineParts = lines[index].split(SPACE_SEPARATOR);
            String operator = lineParts[0];
            int value = DECIMAL_FORMAT.parse(lineParts[1]).intValue();
            switch (operator) {
                case JUMP:
                    index += value;
                    break;
                case ACCUMULATOR:
                    accumulator += value;
                default:
                    index++;
                    break;
            }
        }

        return accumulator;
    }

    public static int fixAndGetAccumulator(final String input) throws ParseException {
        String lines[] = FileUtils.readFile(input).split(LINE_SEPARATOR);
        for (int i = 0; i < lines.length; i++) {
            String[] lineParts = lines[i].split(SPACE_SEPARATOR);
            String operator = lineParts[0];
            if (ACCUMULATOR.equals(operator)) {
                continue;
            }
            String[] modifiedLines = Arrays.copyOf(lines, lines.length);
            String value = lineParts[1];
            if (NO_OP.equals(operator)) {
                modifiedLines[i] = JUMP + " " + value;
            } else {
                modifiedLines[i] = NO_OP + " " + value;
            }
            try {
                return returnAccumulator(modifiedLines);
            } catch (InfiniteLoopException e) {
                continue;
            }
        }
        return -1;
    }

    static class InfiniteLoopException extends Exception {
        private final int accumulator;

        public InfiniteLoopException(final int accumulator) {
            this.accumulator = accumulator;
        }

        public int getAccumulator() {
            return accumulator;
        }
    }
}
