package com.notech.aoc;

import com.notech.aoc.util.FileUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Day7 {

    protected static final String TARGET_BAG = "shiny gold";

    public static int countCanContainShiny(final String file) {
        Map<String, Rule> rules = RuleParser.getRules(file);


        return (int) rules.values().stream()
                          .mapToInt(rule -> countShiny(rule, rules, new ConcurrentHashMap<>()))
                          .filter(e -> e > 0)
                          .count();
    }

    public static int countInsideShiny(final String file) {

        Map<String, Rule> rules = RuleParser.getRules(file);


        return countBagsInside(rules.get(TARGET_BAG), rules, new ConcurrentHashMap<>());
    }

    private static int countShiny(final Rule rule, final Map<String, Rule> rules, final Map<String, Integer> bagsCache) {
        String color = rule.getColor();
        Integer count = bagsCache.get(color);
        if (count != null) {
            return count;
        }

        int foundBags = rule.getBags().entrySet().stream()
                            .mapToInt(entry -> countShiny(entry, rules, bagsCache))
                            .sum();
        bagsCache.put(color, foundBags);
        return foundBags;
    }

    private static int countShiny(final Map.Entry<String, Integer> entry, final Map<String, Rule> rules, final Map<String, Integer> bagsCache) {
        if (TARGET_BAG.equals(entry.getKey())) {
            return entry.getValue();
        }
        return countShiny(rules.get(entry.getKey()), rules, bagsCache);
    }

    private static int countBagsInside(final Rule rule, final Map<String, Rule> rules, final ConcurrentHashMap<String, Integer> bagsCache) {
        return rule.getBags().entrySet().stream()
                   .mapToInt(entry -> countBagsInside(entry, rules, bagsCache))
                   .sum();
    }

    private static int countBagsInside(final Map.Entry<String, Integer> entry, final Map<String, Rule> rules, final ConcurrentHashMap<String, Integer> bagsCache) {
        Integer numberOfBags = entry.getValue();
        String color = entry.getKey();
        Integer bagsInside = bagsCache.get(color);
        if (bagsInside == null) {
            bagsInside = countBagsInside(rules.get(color), rules, bagsCache);
            bagsCache.put(color, bagsInside);
        }
        return numberOfBags + bagsInside * numberOfBags;
    }

    // Not moved to another file to keep 1 file per Day structure
    private static class RuleParser {

        private static Map<String, Rule> getRules(final String file) {
            return FileUtils.executePerLine(file, RuleParser::parseRule)
                            .collect(Collectors.toMap(Rule::getColor, e -> e));
        }

        private static Rule parseRule(final String line) {
            String color = line.split("bags")[0].trim();
            String bagsPart = line.split("contain")[1].trim();
            String[] bagsInsideDetails = bagsPart.split(",");
            Map<String, Integer> bags = Arrays.stream(bagsInsideDetails)
                                              .map(RuleParser::parseBagDetail)
                                              .filter(Objects::nonNull)
                                              .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            return new Rule(color, bags);
        }

        private static Map.Entry<String, Integer> parseBagDetail(final String bagDetail) {
            String quantityPlusColor = bagDetail.split("bag")[0].trim();
            String[] parts = quantityPlusColor.split("\\s+");
            Integer quantity = parseQuantity(parts[0]);
            if (quantity == 0) {
                return null;
            }

            String color = Arrays.stream(parts, 1, parts.length)
                                 .collect(Collectors.joining(" "));

            return new AbstractMap.SimpleEntry<>(color, quantity);
        }

        private static Integer parseQuantity(final String part) {
            try {
                return Integer.valueOf(part);

            } catch (NumberFormatException e) {
                return 0;
            }
        }
    }


    private static class Rule {
        final String color;
        final Map<String, Integer> bags;

        public Rule(final String color, final Map<String, Integer> bags) {
            this.color = color;
            this.bags = bags;
        }

        public String getColor() {
            return color;
        }

        public Map<String, Integer> getBags() {
            return bags;
        }
    }
}
