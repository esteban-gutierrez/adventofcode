package com.adventofcode.day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class HandyHaversacksIncorrect {
    private static final String BAG_RULES = "resources/bag_rules_day_7.txt";
    private static final String TARGET_COLOUR = "shiny gold";

    private static Map<String, BagContent[]> bagRules = new HashMap<>();
    private static List<String> targetRules = new ArrayList<>();

    public static void main(String[] args) {
        targetRules.add(TARGET_COLOUR);
        try (BufferedReader br = new BufferedReader(new FileReader(BAG_RULES))) {
            String line;
            while ((line = br.readLine()) != null) {
                readBagContent(line.replaceAll("bags", "")
                        .replaceAll("bag", "")
                        .replaceAll("\\.", ""));

            }
            boolean keepSearching = true;
            while (keepSearching) {
                keepSearching = findTargetRules();
            }
            System.out.println("Number of bag colors that can eventually contain at least one shiny gold bag: " + targetRules.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void readBagContent(String bagContentString) {
        String[] bagContentRaw = bagContentString.split("contain");
        String bagColour = bagContentRaw[0].trim();
        String[] bags = bagContentRaw[1].split(",");
        BagContent[] allBagContent = new BagContent[bags.length];
        for (int i = 0; i < allBagContent.length; i++) {
            allBagContent[i] = processBagContentString(bags[i].trim());
        }
        bagRules.put(bagColour, allBagContent);
    }

    private static boolean findTargetRules() {
        boolean newRulesFound = false;
        List<String> copyOfTargetRules = new ArrayList<>(targetRules);
        Map<String, BagContent[]> copyOfBagRules = new HashMap<>(bagRules);
        for (String rule : copyOfBagRules.keySet()) {
            for (BagContent bagContent : copyOfBagRules.get(rule)) {
                for (String targetRule : copyOfTargetRules) {
                    if (bagContent != null && bagContent.getBagColour().contains(targetRule)) {
                        targetRules.add(rule);
                        bagRules.remove(rule);
                        newRulesFound = true;
                        System.out.println(rule + " can contain " + bagContent.getBagColour());
                        break;
                    }
                }
            }
        }
        return newRulesFound;
    }

    private static BagContent processBagContentString(String content) {
        if (content.trim().startsWith("no")) {
            return null;
        }
        BagContent bagContent = new BagContent();
        String[] quantityAndColour = content.split(" ");
        bagContent.setQuantity(Integer.valueOf(quantityAndColour[0].trim()));
        bagContent.setBagColour(content.replace(quantityAndColour[0], "").trim());
        return bagContent;
    }
}
