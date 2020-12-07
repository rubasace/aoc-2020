package com.notech.aoc;

import com.notech.aoc.util.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day3Test {

    @Test
    void shouldCount() {
        String map = FileUtils.readFile("day3-test.txt");

        int count = Day3.countTrees(map, 3, 1);

        assertEquals(7, count);
    }

    @Test
    void result1() {
        String map = FileUtils.readFile("day3-input.txt");

        int count = Day3.countTrees(map, 3, 1);

        System.out.println(count);
    }

    @Test
    void shouldMutiply() {
        String map = FileUtils.readFile("day3-test.txt");

        int count = Day3.countMultiplying(map, new int[]{1, 3, 5, 7, 1}, new int[]{1, 1, 1, 1, 2});

        assertEquals(336, count);
    }

    @Test
    void result2() {
        String map = FileUtils.readFile("day3-input.txt");

        int count = Day3.countMultiplying(map, new int[]{1, 3, 5, 7, 1}, new int[]{1, 1, 1, 1, 2});

        System.out.println(count);
    }
}