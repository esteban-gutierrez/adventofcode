package com.adventofcode.day4;

public enum ValidField {
    BIRTH_YEAR("byr"),
    ISSUE_YEAR("iyr"),
    EXPIRATION_YEAR("eyr"),
    HEIGHT("hgt"),
    HAIR_COLOR("hcl"),
    EYE_COLOR("ecl"),
    PASSPORT_ID("pid"),
    COUNTRY_ID("cid");

    private final String code;

    ValidField(String code) {
        this.code = code;
    }
}
