package com.notech.aoc;

import java.util.HashMap;
import java.util.Map;

public class Day15 {

    public static int findNthSpokenNumber(final int position, final int... numbers) {
        Map<Integer, Integer> lastSpokenMemory = new HashMap<>();
        int current = numbers[0];
        int previous;
        for (int i = 1; i < position; i++) {
            previous = current;
            if (i < numbers.length) {
                current = numbers[i];
            } else {
                Integer lastSpoken = lastSpokenMemory.getOrDefault(previous, i - 1);
                current = i - 1 - lastSpoken;

            }
            lastSpokenMemory.put(previous, i - 1);
        }

        return current;
    }
}
