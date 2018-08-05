package com.zwemmen.psv.meet;

/**
 * Types of strokes available in the meets or programs.
 *
 * @author afernandez
 */
public enum Stroke {
    FREESTYLE("Vrije Slag"),
    BREASTSTROKE("Schoolslag"),
    BACKSTROKE("Rugslag"),
    BUTTERFLY("Vlinderslag"),
    MEDLEY("Wisselslag");

    private String value;

    Stroke(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}