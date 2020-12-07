package com.notech.aoc;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Day2 {

    static boolean applyPolicy1(final String password) {
        Input input = parseInput(password);
        long characterOccurrences = input.getPassword().chars()
                                         .filter(c -> c == input.getCharacter())
                                         .count();
        return characterOccurrences >= input.getFirstNumber() && characterOccurrences <= input.getSecondNumber();
    }

    static boolean applyPolicy2(final String password) {
        Input input = parseInput(password);

        char firstChar = input.getPassword().charAt(input.getFirstNumber() - 1);
        char secondChar = input.getPassword().charAt(input.getSecondNumber() - 1);
        return firstChar == input.getCharacter() ^ secondChar == input.getCharacter();
    }

    private static Input parseInput(final String password) {
        String[] parts = password.split(":");
        String policy = parts[0].trim();
        String value = parts[1].trim();
        String[] policyParts = policy.split("\\s+");
        int[] range = Arrays.stream(policyParts[0].split("-"))
                            .mapToInt(Integer::valueOf)
                            .toArray();
        char character = policyParts[1].charAt(0);

        return new Input(character, range[0], range[1], value);
    }

    static class Input {
        private char character;
        private int firstNumber;
        private int secondNumber;
        private String password;

        public Input(final char character, final int firstNumber, final int secondNumber, final String password) {
            this.character = character;
            this.firstNumber = firstNumber;
            this.secondNumber = secondNumber;
            this.password = password;
        }

        public char getCharacter() {
            return character;
        }

        public int getFirstNumber() {
            return firstNumber;
        }

        public int getSecondNumber() {
            return secondNumber;
        }

        public String getPassword() {
            return password;
        }
    }
}
