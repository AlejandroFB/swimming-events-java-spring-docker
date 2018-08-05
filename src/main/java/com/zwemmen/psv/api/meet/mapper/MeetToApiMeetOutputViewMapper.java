package com.zwemmen.psv.api.meet.mapper;

import com.zwemmen.psv.api.meet.ApiMeetOutputView;
import com.zwemmen.psv.api.generic.mapper.MappingDefinition;
import com.zwemmen.psv.meet.Meet;
import org.springframework.stereotype.Component;

/**
 * Mapper from Meet object to ApiMeetOutputView entity.
 *
 * @author afernandez
 */
@Component
public class MeetToApiMeetOutputViewMapper implements MappingDefinition<Meet, ApiMeetOutputView> {

    @Override
    public ApiMeetOutputView map(Meet meet) {
        return new ApiMeetOutputView.Builder()
                .id(meet.getId())
                .programNumber(meet.getProgramNumber())
                .distance(meet.getDistance().getValue())
                .stroke(meet.getStroke().getValue())
                .ageGroup(meet.getAgeGroup())
                .build();
    }

    @Override
    public ApiMeetOutputView map(Meet meet, ApiMeetOutputView apiMeetOutputView) {
        throw new UnsupportedOperationException("Mapping to an existing entity not yet implemented.");
    }
}