package com.zwemmen.psv.api.meet.mapper;

import com.zwemmen.psv.api.meet.ApiMeetInputView;
import com.zwemmen.psv.api.generic.converter.EnumConverter;
import com.zwemmen.psv.api.generic.mapper.MappingDefinition;
import com.zwemmen.psv.meet.Distance;
import com.zwemmen.psv.meet.Meet;
import com.zwemmen.psv.meet.Stroke;
import org.springframework.stereotype.Component;

/**
 * Mapper from ApiMeetInputView object to Meet entity.
 *
 * @author afernandez
 */
@Component
public class ApiMeetInputViewToMeetMapper implements MappingDefinition<ApiMeetInputView, Meet> {

    @Override
    public Meet map(ApiMeetInputView apiMeetInputView) {
        final EnumConverter<Distance, String> distanceConverter = new EnumConverter<>(Distance.class);
        final EnumConverter<Stroke, String> strokeConverter = new EnumConverter<>(Stroke.class);

        return new Meet.Builder()
                .id(apiMeetInputView.getId())
                .programNumber(apiMeetInputView.getProgramNumber())
                .distance(distanceConverter.convert(apiMeetInputView.getDistance()))
                .stroke(strokeConverter.convert(apiMeetInputView.getStroke()))
                .ageGroup(apiMeetInputView.getAgeGroup())
                .build();
    }

    @Override
    public Meet map(ApiMeetInputView apiMeetInputView, Meet meet) {
        throw new UnsupportedOperationException("Mapping to an existing entity not yet implemented.");
    }
}