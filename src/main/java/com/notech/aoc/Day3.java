package com.notech.aoc;

import com.notech.aoc.util.FileUtils;

import java.util.stream.IntStream;

public class Day3 {

    protected static final char TREE = '#';

    static int countTrees(final String map, final int right, final int down) {
        int x = 0;
        int y = 0;
        int count = 0;
        String lines[] = FileUtils.splitByLine(map);
        while (y < lines.length) {
            String line = lines[y];
            if (line.charAt(x % line.length()) == TREE) {
                count++;
            }
            y += down;
            x += right;
        }
        return count;
    }

    static int countMultiplying(final String map, final int[] right, final int[] down) {
        if (right.length != down.length) {
            throw new IllegalArgumentException("right and down arrays must have same lengths");
        }

        return IntStream.range(0, right.length)
                        .map(i -> countTrees(map, right[i], down[i]))
                        .reduce(1, (a, b) -> a * b);

    }
}

