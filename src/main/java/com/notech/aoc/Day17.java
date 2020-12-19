package com.notech.aoc;

import com.notech.aoc.util.FileUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day17 {

    private static final char ACTIVE = '#';

    public static long countActiveCubes(final String fileName, final int cycles, boolean increaseZ) {
        List<String> lines = FileUtils.getLines(fileName)
                                      .collect(Collectors.toList());
        int yMin = 0;
        int yMax = lines.size();
        int xMin = 0;
        int xMax = lines.get(0).length();
        int zMin = 0;
        int zMax = 0;
        int wMin = 0;
        int wMax = 0;
        Set<String> activeCubes = initialActiveCubes(lines);

        for (int i = 0; i < cycles; i++) {
            xMin--;
            yMin--;
            wMin--;
            xMax++;
            yMax++;

            wMax++;
            if (increaseZ) {
                zMin--;
                zMax++;
            }
            activeCubes = mutate(xMin, xMax, yMin, yMax, zMin, zMax, wMin, wMax, activeCubes);
        }


        return activeCubes.size();
    }

    private static Set<String> mutate(final int xMin, final int xMax, final int yMin, final int yMax, final int zMin, final int zMax, final int wMin, final int wMax, final Set<String> activeCubes) {
        Set<String> newActiveCubes = new HashSet<>(activeCubes);
        for (int x = xMin; x <= xMax; x++) {
            for (int y = yMin; y <= yMax; y++) {
                for (int z = zMin; z <= zMax; z++) {
                    for (int w = wMin; w <= wMax; w++) {
                        String key = toKey(x, y, z, w);
                        boolean isActive = activeCubes.contains(key);
                        int activeAdjacents = countAdjacents(x, y, z, w, activeCubes);
                        if (isActive && (activeAdjacents < 2 || activeAdjacents > 3)) {
                            newActiveCubes.remove(key);
                        } else if (!isActive && activeAdjacents == 3) {
                            newActiveCubes.add(key);
                        }
                    }
                }
            }
        }
        return newActiveCubes;
    }

    private static int countAdjacents(final int targetX, final int targetY, final int targetZ, final int targetW, final Set<String> activeCubes) {
        int count = 0;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    for (int w = -1; w <= 1; w++) {
                        if (x == 0 && y == 0 && z == 0 && w == 0) {
                            continue;
                        }
                        if (activeCubes.contains(toKey(targetX + x, targetY + y, targetZ + z, targetW + w))) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    private static Set<String> initialActiveCubes(final List<String> lines) {
        Set<String> activeCubes = new HashSet<>();
        for (int y = 0; y < lines.size(); y++) {
            for (int x = 0; x < lines.get(y).length(); x++) {
                if (lines.get(y).charAt(x) == ACTIVE) {
                    activeCubes.add(toKey(x, y, 0, 0));
                }
            }
        }
        return activeCubes;
    }

    private static String toKey(final int x, final int y, final int z, final int w) {
        return String.format("%d|%d|%d|%d", x, y, z, w);
    }


}
