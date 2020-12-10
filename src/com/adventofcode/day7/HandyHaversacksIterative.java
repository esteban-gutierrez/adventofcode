package com.adventofcode.day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class HandyHaversacksIterative {
    private static final String BAG_RULES = "resources/day_7_bag_rules.txt";
    private static final String TARGET_COLOUR = "shiny gold";

    private static Map<String, BagContent[]> bagRules = new HashMap<>();
    private static Map<String, String> targetRules = new HashMap<>();
    private static Map<String, String> containerBags = new HashMap<>();

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(BAG_RULES))) {
            String line;
            while ((line = br.readLine()) != null) {
                readBagContent(line.replaceAll("bags", "")
                        .replaceAll("bag", "")
                        .replaceAll("\\.", ""));

            }
            targetRules.put(TARGET_COLOUR, TARGET_COLOUR);
            boolean keepSearching = true;
            while (keepSearching) {
                keepSearching = findContainerBags();
            }
            System.out.println("Number of bag colors that can eventually contain at least one shiny gold bag: " + containerBags.size());
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

    private static boolean findContainerBags() {
        boolean newBagsFound = false;
        Map<String, String> copyOfTargetRules = new HashMap<>(targetRules);
        System.out.println("Searching for bags that contain: " + targetRules.keySet());
        for (String targetRule : copyOfTargetRules.keySet()) {
            targetRules.remove(targetRule);
            for (String rule : bagRules.keySet()) {
                for (BagContent bagContent : bagRules.get(rule)) {
                    if (bagContent != null && bagContent.getBagColour().contains(targetRule)) {
                        newBagsFound = true;
                        targetRules.put(rule, rule);
                        containerBags.put(rule, rule);
                        System.out.println("\t" + rule + " can contain " + bagContent.getBagColour());
                        break;
                    }
                }
            }
        }
        System.out.println("\t\tBags found: " + targetRules.size());
        return newBagsFound;
    }
}
