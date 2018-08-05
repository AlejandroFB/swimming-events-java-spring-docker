package com.zwemmen.psv.api.competition.mapper;

import com.zwemmen.psv.api.generic.converter.EnumConverter;
import com.zwemmen.psv.event.competition.Competition;
import com.zwemmen.psv.api.competition.ApiCompetitionInputView;
import com.zwemmen.psv.event.competition.RaceCourse;
import com.zwemmen.psv.api.generic.mapper.MappingDefinition;
import org.springframework.stereotype.Component;

/**
 * Mapper from ApiCompetitionInputView object to Competition entity.
 *
 * @author afernandez
 */
@Component
public class ApiCompetitionInputViewToCompetitionMapper implements MappingDefinition<ApiCompetitionInputView, Competition> {

    @Override
    public Competition map(ApiCompetitionInputView apiCompetitionInputView) {
        final EnumConverter<RaceCourse, String> courseConverter = new EnumConverter<>(RaceCourse.class);

        return new Competition.Builder()
                .id(apiCompetitionInputView.getId())
                .number(apiCompetitionInputView.getNumber())
                .course(courseConverter.convert(apiCompetitionInputView.getCourse()))
                .place(apiCompetitionInputView.getPlace())
                .startDate(apiCompetitionInputView.getStartDate())
                .endDate(apiCompetitionInputView.getEndDate())
                .build();
    }

    @Override
    public Competition map(ApiCompetitionInputView apiCompetitionInputView, Competition competition) {
        throw new UnsupportedOperationException("Mapping to an existing entity not yet implemented.");
    }
}