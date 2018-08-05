package com.zwemmen.psv.api.coach.mapper;

import com.zwemmen.psv.api.coach.ApiCoachInputView;
import com.zwemmen.psv.api.generic.mapper.MappingDefinition;
import com.zwemmen.psv.coach.Coach;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Mapper from ApiCoachInputView object to Coach entity.
 *
 * @author afernandez
 */
@Component
public class ApiCoachInputViewToCoachMapper implements MappingDefinition<ApiCoachInputView, Coach> {

    @Override
    public Coach map(ApiCoachInputView apiCoachInputView) {
        final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        return new Coach.Builder()
                .id(apiCoachInputView.getId())
                .emailAddress(apiCoachInputView.getEmailAddress())
                .encryptedPassword(passwordEncoder.encode(apiCoachInputView.getPassword()))
                .name(apiCoachInputView.getName())
                .club(apiCoachInputView.getClub())
                .build();
    }

    @Override
    public Coach map(ApiCoachInputView apiCoachInputView, Coach coach) {
        throw new UnsupportedOperationException("Mapping to an existing entity not yet implemented.");
    }
}