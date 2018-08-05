package com.zwemmen.psv.api.competition;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zwemmen.psv.api.generic.ApiOutputView;

import java.time.LocalDate;

/**
 * Competition output view including the data that is going to be returned to the client.
 *
 * @author afernandez
 */
public class ApiCompetitionOutputView implements ApiOutputView {
    private Integer id;
    private Integer number;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private String place;
    private String course;

    public ApiCompetitionOutputView() {
    }

    public ApiCompetitionOutputView(Builder builder) {
        this.id = builder.id;
        this.number = builder.number;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.place = builder.place;
        this.course = builder.course;
    }

    public Integer getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getPlace() {
        return place;
    }

    public String getCourse() {
        return course;
    }

    public static class Builder {
        private Integer id;
        private Integer number;
        private LocalDate startDate;
        private LocalDate endDate;
        private String place;
        private String course;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder number(Integer number) {
            this.number = number;
            return this;
        }

        public Builder startDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder place(String place) {
            this.place = place;
            return this;
        }

        public Builder course(String course) {
            this.course = course;
            return this;
        }

        public ApiCompetitionOutputView build() { return new ApiCompetitionOutputView(this); }
    }
}