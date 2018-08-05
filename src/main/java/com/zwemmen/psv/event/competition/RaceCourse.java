package com.zwemmen.psv.event.competition;

/**
 * Swimming pool race course.
 * Long course: 50m
 * Short course: 25m
 *
 * @author afernandez
 */
public enum RaceCourse {
    LONG("50m"),
    SHORT("25m");

    private String value;

    RaceCourse(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}