package com.notech.aoc;

import com.notech.aoc.util.FileUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day14 {


    protected static final String MASK_ACTION = "mask";
    protected static final char MASK_IGNORE_V1 = 'X';
    protected static final char MASK_IGNORE_V2 = '0';
    protected static final char FLOATING_MASK = 'X';
    protected static final char COLLISION_FLAG = '!';

    public static long sumAllValues(final String fileName) {
        Map<String, Long> memory = new HashMap<>();
        AtomicReference<char[]> mask = new AtomicReference<>(new char[0]);
        FileUtils.getLines(fileName)
                 .forEach(line -> process(line, mask, memory));

        return memory.values().stream()
                     .mapToLong(e -> e)
                     .sum();
    }

    public static long sumAllValuesV2(final String fileName) {
        AtomicReference<char[]> mask = new AtomicReference<>(new char[0]);
        List<MemoryAddress> addresses = FileUtils.getLines(fileName)
                                                 .map(line -> processV2(line, mask))
                                                 .filter(Optional::isPresent)
                                                 .map(Optional::get)
                                                 .collect(Collectors.toList());

        for (int i = 0; i < addresses.size() - 1; i++) {
            MemoryAddress address = addresses.get(i);
            addresses.subList(i + 1, addresses.size())
                     .forEach(a -> address.setAddress(removeDuplicates(address.getAddress(), a.getAddress())));
        }
        return addresses.stream()
                        .mapToLong(address -> address.getValue() * getMemoryPositions(address.getAddress()))
                        .sum();
    }

    private static long getMemoryPositions(final String address) {
        long occurrences = address.chars()
                                  .filter(c -> c == FLOATING_MASK)
                                  .count();
        return (long) Math.pow(2, occurrences);
    }

    private static void process(final String line, final AtomicReference<char[]> mask, final Map<String, Long> memory) {
        String[] parts = line.split("=");
        String action = parts[0].trim();
        String value = parts[1].trim();
        if (MASK_ACTION.equals(action)) {
            mask.set(value.toCharArray());
        } else {
            String memPosition = action.split("\\[|\\]")[1];
            Long maskedValue = applyMask(value, mask.get());
            memory.put(memPosition, maskedValue);
        }
    }

    private static Optional<MemoryAddress> processV2(final String line, final AtomicReference<char[]> mask) {
        String[] parts = line.split("=");
        String action = parts[0].trim();
        String value = parts[1].trim();
        if (MASK_ACTION.equals(action)) {
            mask.set(value.toCharArray());
            return Optional.empty();
        } else {
            String memPosition = action.split("\\[|\\]")[1];
            String address = applyMaskV2(memPosition, mask.get());
            Long longValue = Long.parseLong(value);
            return Optional.of(new MemoryAddress(address, longValue));
        }
    }

    private static Long applyMask(final String value, final char[] mask) {
        char[] binaryChars = getBinaryChars(value, mask);

        IntStream.range(0, mask.length)
                 .filter(i -> mask[i] != MASK_IGNORE_V1)
                 .forEach(i -> binaryChars[i] = mask[i]);

        return Long.parseLong(new String(binaryChars), 2);

    }

    private static String removeDuplicates(final String current, final String next) {
        char[] chars = current.toCharArray();
        for (int i = 0; i < current.length(); i++) {
            char char1 = current.charAt(i);
            char char2 = next.charAt(i);
            if (char1 == FLOATING_MASK) {
                if (char2 == FLOATING_MASK) {
                    chars[i] = COLLISION_FLAG;
                }
            } else if (char2 != FLOATING_MASK && char1 != char2) {
                return current;
            }
        }
        return new String(chars);

    }

    private static String applyMaskV2(final String value, final char[] mask) {
        char[] binaryChars = getBinaryChars(value, mask);

        IntStream.range(0, mask.length)
                 .filter(i -> mask[i] != MASK_IGNORE_V2)
                 .forEach(i -> binaryChars[i] = mask[i]);

        return new String(binaryChars);
        //
        //        return Long.parseLong(new String(binaryChars), 2);

    }

    private static char[] getBinaryChars(final String value, final char[] mask) {
        int intValue = Integer.parseInt(value);
        String binaryValue = Integer.toBinaryString(intValue);
        int missingBits = mask.length - binaryValue.length();
        if (missingBits > 0) {
            String prefix = IntStream.range(0, missingBits)
                                     .mapToObj(i -> "0")
                                     .collect(Collectors.joining());
            binaryValue = prefix + binaryValue;
        }
        char[] binaryChars = binaryValue.toCharArray();
        return binaryChars;
    }

    private static class MemoryAddress {
        private String address;
        private Long value;

        public MemoryAddress(final String address, final Long value) {
            this.address = address;
            this.value = value;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(final String address) {
            this.address = address;
        }

        public Long getValue() {
            return value;
        }

        public void setValue(final Long value) {
            this.value = value;
        }
    }
}
