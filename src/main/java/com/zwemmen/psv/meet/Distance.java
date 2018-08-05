package com.zwemmen.psv.meet;

/**
 * Distances in meters available in the meets/programs.
 *
 * @author afernandez
 */
public enum Distance {
    FIFTY("50"),
    ONE_HUNDRED("100"),
    TWO_HUNDRED("200"),
    FOUR_HUNDRED("400"),
    EIGHT_HUNDRED("800");

    private String value;

    Distance(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}