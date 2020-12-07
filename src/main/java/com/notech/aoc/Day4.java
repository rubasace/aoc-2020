package com.notech.aoc;

import com.notech.aoc.util.FileUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Day4 {

    protected static final String KEY_VALUE_SEPARATOR = ":";
    protected static final String FIELD_SEPARATOR = "\\n|\\s+";
    protected static final String BIRTH_YEAR = "byr";
    protected static final String ISSUE_YEAR = "iyr";
    protected static final String EXPIRATION_YEAR = "eyr";
    protected static final String HEIGHT = "hgt";
    protected static final String HAIR_COLOR = "hcl";
    protected static final String EYE_COLOR = "ecl";
    protected static final String PASSPORT_ID = "pid";
    protected static final Set<String> MANDATORY_FIELDS = Set.of(BIRTH_YEAR, ISSUE_YEAR, EXPIRATION_YEAR, HEIGHT, HAIR_COLOR, EYE_COLOR, PASSPORT_ID);
    protected static final Set<String> EYE_COLORS = Set.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth");

    public static int checkAllFieldsButCidArePresent(final String file) {
        return validate(file, Day4::hasAllFieldsIgnoringCid);
    }

    public static int validateIgnoringCid(final String file) {
        return validate(file, Day4::validateAllFieldsIgnoringCid);
    }

    private static int validate(final String file, final Predicate<Map<String, String>> validation) {
        String content = FileUtils.readFile(file);
        String[] passports = FileUtils.splitByBlankSpace(content);

        return (int) Arrays.stream(passports)
                           .map(Day4::parse)
                           .filter(validation::test)
                           .count();
    }

    private static Map<String, String> parse(final String input) {
        String[] data = input.split(FIELD_SEPARATOR);
        return Arrays.stream(data)
                     .collect(Collectors.toMap(e -> e.split(KEY_VALUE_SEPARATOR)[0], e -> e.split(KEY_VALUE_SEPARATOR)[1]));

    }

    private static boolean hasAllFieldsIgnoringCid(final Map<String, String> passportData) {
        return MANDATORY_FIELDS.stream()
                               .allMatch(field -> isPresent(passportData.get(field)));

    }

    private static boolean validateAllFieldsIgnoringCid(final Map<String, String> passportData) {
        if (!hasAllFieldsIgnoringCid(passportData)) {
            return false;
        }
        return MANDATORY_FIELDS.stream()
                               .allMatch(field -> isValid(field, passportData));

    }

    private static boolean isPresent(final String value) {
        return value != null && !value.isBlank();
    }

    private static boolean isValid(final String field, final Map<String, String> passportData) {
        String value = passportData.get(field);
        switch (field) {
            case BIRTH_YEAR:
                return isBetween(value, 1920, 2002);
            case ISSUE_YEAR:
                return isBetween(value, 2010, 2020);
            case EXPIRATION_YEAR:
                return isBetween(value, 2020, 2030);
            case HEIGHT:
                return validateHeight(value);
            case HAIR_COLOR:
                return validateHairColor(value);
            case EYE_COLOR:
                return validateEyeColor(value);
            case PASSPORT_ID:
                return validatePassportId(value);
            default:
                return true;
        }
    }

    private static boolean isBetween(final String value, final int min, final int max) {
        try {
            int birthYear = Integer.parseInt(value);
            return birthYear >= min && birthYear <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean validateEyeColor(final String value) {
        return EYE_COLORS.contains(value);
    }

    private static boolean validateHairColor(final String value) {
        if (!value.startsWith("#")) {
            return false;
        }
        String color = value.substring(1);
        return color.length() == 6 && color.chars()
                                           .allMatch(c -> (c >= '0' && c <= '9') || (c >= 'a' && c <= 'f'));
    }

    private static boolean validatePassportId(final String value) {
        return value.length() == 9 && value.chars()
                                           .allMatch(c -> c >= '0' && c <= '9');
    }

    private static String removeSuffix(final String value, final String word) {
        return value.substring(0, value.indexOf(word));
    }

    private static boolean validateHeight(final String value) {
        if (value.endsWith("cm")) {
            return isBetween(removeSuffix(value, "cm"), 150, 193);
        } else if (value.endsWith("in")) {
            return isBetween(removeSuffix(value, "in"), 59, 76);
        } else {
            return false;
        }
    }

}