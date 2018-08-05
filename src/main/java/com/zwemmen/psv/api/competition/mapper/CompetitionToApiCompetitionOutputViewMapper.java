package com.zwemmen.psv.api.competition.mapper;

import com.zwemmen.psv.api.competition.ApiCompetitionOutputView;
import com.zwemmen.psv.event.competition.Competition;
import com.zwemmen.psv.api.generic.mapper.MappingDefinition;
import org.springframework.stereotype.Component;

/**
 * Mapper from Competition object to ApiCompetitionOutputView entity.
 *
 * @author afernandez
 */
@Component
public class CompetitionToApiCompetitionOutputViewMapper implements MappingDefinition<Competition, ApiCompetitionOutputView> {

    @Override
    public ApiCompetitionOutputView map(Competition competition) {
        return new ApiCompetitionOutputView.Builder()
                .id(competition.getId())
                .number(competition.getNumber())
                .place(competition.getPlace())
                .course(competition.getCourse().getValue())
                .startDate(competition.getStartDate())
                .endDate(competition.getEndDate())
                .build();
    }

    @Override
    public ApiCompetitionOutputView map(Competition competition, ApiCompetitionOutputView apiCompetitionOutputView) {
        throw new UnsupportedOperationException("Mapping to an existing entity not yet implemented.");
    }
}