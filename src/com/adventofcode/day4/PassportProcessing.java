package com.adventofcode.day4;

import java.io.BufferedReader;
import java.io.FileReader;

public class PassportProcessing {
    private static final String PASSPORTS_INPUT = "resources/day_4_passports.txt";

    public static void main (String [] args) {
        int totalPassports = 1;
        int validPassports = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(PASSPORTS_INPUT))) {
            String line;
            PassportBean passportBean = new PassportBean();
            while ((line = br.readLine()) != null) {
                if (line.trim().length() == 0) {
                    System.out.println(totalPassports + "\t" + passportBean.toString());
                    if (passportBean.areRequiredFieldsPopulated()) {
                        validPassports++;
                    }
                    passportBean = new PassportBean();
                    totalPassports++;
                } else {
                    populatePassport(passportBean, line);
                }
            }
            // Last password
            System.out.println(totalPassports + "\t" + passportBean.toString());
            if (passportBean.areRequiredFieldsPopulated()) {
                validPassports++;
            }
            totalPassports++;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Valid passports: " + validPassports);
    }

    private static void populatePassport(PassportBean passportBean, String line) {
        String [] data = line.split(" ");
        for (String field : data) {
            String [] fieldData = field.split(":");
            ValidField validField = readValidField(fieldData[0]);
            if (validField != null) {
                passportBean.setProperty(validField, fieldData[1]);
            }
        }
    }

    private static ValidField readValidField(String fieldName) {
        ValidField validField = null;
        try {
            validField = ValidField.valueOf(fieldName);
        } catch (IllegalArgumentException iae) {
            // Ignore this field
        }
        return validField;
    }

}
