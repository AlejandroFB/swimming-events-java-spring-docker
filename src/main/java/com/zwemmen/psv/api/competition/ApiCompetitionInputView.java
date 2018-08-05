package com.zwemmen.psv.api.competition;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zwemmen.psv.api.generic.ApiInputView;

import java.time.LocalDate;

/**
 * Competition input view including the data necessary send by the client.
 *
 * @author afernandez
 */
public class ApiCompetitionInputView implements ApiInputView {
    private Integer id;
    private Integer number;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private String place;
    private String course;

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
}