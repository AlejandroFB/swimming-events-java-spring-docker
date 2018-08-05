package com.zwemmen.psv.api.swimmer.mapper;

import com.zwemmen.psv.api.swimmer.ApiSwimmerInputView;
import com.zwemmen.psv.api.generic.converter.EnumConverter;
import com.zwemmen.psv.api.generic.mapper.MappingDefinition;
import com.zwemmen.psv.swimmer.Level;
import com.zwemmen.psv.swimmer.Swimmer;
import com.zwemmen.psv.swimmer.SwimmerProfile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Mapper from ApiSwimmerInputView object to Swimmer entity.
 *
 * @author afernandez
 */
@Component
public class ApiSwimmerInputViewToSwimmerMapper implements MappingDefinition<ApiSwimmerInputView, Swimmer> {

    @Override
    public Swimmer map(ApiSwimmerInputView apiSwimmerInputView) {
        final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        final EnumConverter<Level, String> levelConverter = new EnumConverter<>(Level.class);

        final SwimmerProfile swimmerProfile = new SwimmerProfile.Builder()
                .name(apiSwimmerInputView.getName())
                .emailAddress(apiSwimmerInputView.getEmailAddress())
                .address(apiSwimmerInputView.getAddress())
                .city(apiSwimmerInputView.getCity())
                .phone(apiSwimmerInputView.getPhone())
                .mobilePhone(apiSwimmerInputView.getMobilePhone())
                .birthday(apiSwimmerInputView.getBirthday())
                .build();

        return new Swimmer.Builder()
                .id(apiSwimmerInputView.getId())
                .number(apiSwimmerInputView.getNumber())
                .userName(apiSwimmerInputView.getUserName())
                .registrationId(apiSwimmerInputView.getRegistrationId())
                .encryptedPassword(passwordEncoder.encode(apiSwimmerInputView.getPassword()))
                .club(apiSwimmerInputView.getClub())
                .level(levelConverter.convert(apiSwimmerInputView.getLevel()))
                .swimmerProfile(swimmerProfile)
                .build();
    }

    @Override
    public Swimmer map(ApiSwimmerInputView apiSwimmerInputView, Swimmer swimmer) {
        throw new UnsupportedOperationException("Mapping to an existing entity not yet implemented.");
    }
}