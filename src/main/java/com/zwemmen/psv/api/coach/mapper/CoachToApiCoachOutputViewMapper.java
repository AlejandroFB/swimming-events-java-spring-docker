package com.zwemmen.psv.api.coach.mapper;

import com.zwemmen.psv.api.coach.ApiCoachOutputView;
import com.zwemmen.psv.api.generic.mapper.MappingDefinition;
import com.zwemmen.psv.coach.Coach;
import org.springframework.stereotype.Component;

/**
 * Mapper from Coach object to ApiCoachOutputView entity.
 *
 * @author afernandez
 */
@Component
public class CoachToApiCoachOutputViewMapper implements MappingDefinition<Coach, ApiCoachOutputView> {

    @Override
    public ApiCoachOutputView map(Coach coach) {
        return new ApiCoachOutputView.Builder()
                .id(coach.getId())
                .emailAddress(coach.getEmailAddress())
                .encryptedPassword(coach.getEncryptedPassword())
                .name(coach.getName())
                .club(coach.getClub())
                .tokenValidAfter(coach.getTokenValidAfter())
                .build();
    }

    @Override
    public ApiCoachOutputView map(Coach coach, ApiCoachOutputView apiCoachOutputView) {
        throw new UnsupportedOperationException("Mapping to an existing entity not yet implemented.");
    }
}