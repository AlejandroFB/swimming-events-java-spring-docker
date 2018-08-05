package com.zwemmen.psv.api.meet;

import com.zwemmen.psv.api.generic.ApiOutputView;

/**
 * Meet output view including the data that is going to be returned to the client.
 *
 * @author afernandez
 */
public class ApiMeetOutputView implements ApiOutputView {
    private Integer id;
    private Integer programNumber;
    private String distance;
    private String stroke;
    private String ageGroup;

    public ApiMeetOutputView() {
    }

    public ApiMeetOutputView(Builder builder) {
        this.id = builder.id;
        this.programNumber = builder.programNumber;
        this.distance = builder.distance;
        this.stroke = builder.stroke;
        this.ageGroup = builder.ageGroup;
    }

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

    public static class Builder {
        private Integer id;
        private Integer programNumber;
        private String distance;
        private String stroke;
        private String ageGroup;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder programNumber(Integer programNumber) {
            this.programNumber = programNumber;
            return this;
        }

        public Builder distance(String distance) {
            this.distance = distance;
            return this;
        }

        public Builder stroke(String stroke) {
            this.stroke = stroke;
            return this;
        }

        public Builder ageGroup(String ageGroup) {
            this.ageGroup = ageGroup;
            return this;
        }

        public ApiMeetOutputView build() { return new ApiMeetOutputView(this); }
    }
}