package com.zwemmen.psv.api.meet;

import com.zwemmen.psv.api.generic.ApiInputView;

/**
 * Meet input view including the data sent from the client.
 * Includes its related competition number.
 *
 * @author afernandez
 */
public class ApiMeetInputView implements ApiInputView {
    private Integer id;
    private Integer programNumber;
    private String distance;
    private String stroke;
    private String ageGroup;

    public Integer getId() {
        return id;
    }

    public Integer getProgramNumber() {
        return programNumber;
    }

    public String getDistance() {
        return distance;
    }

    public String getStroke() {
        return stroke;
    }

    public String getAgeGroup() {
        return ageGroup;
    }
}