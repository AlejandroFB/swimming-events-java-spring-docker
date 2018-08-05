package com.zwemmen.psv.api.swimmer.mapper;

import com.zwemmen.psv.api.swimmer.ApiSwimmerOutputView;
import com.zwemmen.psv.api.generic.mapper.MappingDefinition;
import com.zwemmen.psv.swimmer.Swimmer;
import com.zwemmen.psv.swimmer.SwimmerProfile;
import org.springframework.stereotype.Component;

/**
 * Mapper from Swimmer object to ApiSwimmerOutputView entity.
 *
 * @author afernandez
 */
@Component
public class SwimmerToApiSwimmerOutputViewMapper implements MappingDefinition<Swimmer, ApiSwimmerOutputView> {

    @Override
    public ApiSwimmerOutputView map(Swimmer swimmer) {
        final SwimmerProfile swimmerProfile = swimmer.getSwimmerProfile();
        return new ApiSwimmerOutputView.Builder()
                .id(swimmer.getId())
                .userName(swimmer.getUserName())
                .encryptedPassword(swimmer.getEncryptedPassword())
                .number(swimmer.getNumber())
                .registrationId(swimmer.getRegistrationId())
                .club(swimmer.getClub())
                .level(swimmer.getLevel().toString())
                .name(swimmerProfile.getName())
                .emailAddress(swimmerProfile.getEmailAddress())
                .city(swimmerProfile.getCity())
                .address(swimmerProfile.getAddress())
                .phone(swimmerProfile.getPhone())
                .mobilePhone(swimmerProfile.getMobilePhone())
                .birthday(swimmerProfile.getBirthday())
                .build();
    }

    @Override
    public ApiSwimmerOutputView map(Swimmer swimmer, ApiSwimmerOutputView apiSwimmerOutputView) {
        throw new UnsupportedOperationException("Mapping to an existing entity not yet implemented.");
    }
}