package com.adventofcode.day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class BagsInsideTheShinyGoldOne {
    private static final String BAG_RULES = "resources/bag_rules_day_7.txt";
    private static final String TARGET_COLOUR = "shiny gold";

    public static void main(String[] args) {
        Map<String, BagContent[]> bagRules = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(BAG_RULES))) {
            String line;
            while ((line = br.readLine()) != null) {
                readBagContent(bagRules, line.replaceAll("bags", "")
                        .replaceAll("bag", "")
                        .replaceAll("\\.", ""));

            }
            System.out.println("Number of bags: " + (countBags(bagRules, TARGET_COLOUR) - 1)); // -1 to not count the shiny bag itself

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void readBagContent(Map<String, BagContent[]> bagRules, String bagContentString) {
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

    private static int countBags(Map<String, BagContent[]> bagRules, String bagColour) {
        int numberOfBags = 1;
        BagContent[] bagContents = bagRules.get(bagColour);
        for (BagContent bagContent : bagContents) {
            if (bagContent != null) {
                numberOfBags += (bagContent.getQuantity() * countBags(bagRules, bagContent.getBagColour()));
            } else {
                return 1;
            }
        }
        return numberOfBags;
    }
}
