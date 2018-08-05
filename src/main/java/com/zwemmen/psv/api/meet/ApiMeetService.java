package com.zwemmen.psv.api.meet;

import com.zwemmen.psv.api.exception.ApiResourceNotFoundException;
import com.zwemmen.psv.api.generic.ApiService;
import com.zwemmen.psv.api.meet.mapper.ApiMeetInputViewToMeetMapper;
import com.zwemmen.psv.api.meet.mapper.MeetToApiMeetOutputViewMapper;
import com.zwemmen.psv.meet.BaseMeetService;
import com.zwemmen.psv.meet.Meet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service layer of meets API module. Makes use of meets related services
 * to get or update data.
 *
 * @author afernandez
 */
@Service
public class ApiMeetService implements ApiService<ApiMeetInputView, ApiMeetOutputView, Integer> {

    @Autowired
    private BaseMeetService meetService;
    @Autowired
    private MeetToApiMeetOutputViewMapper meetOutputViewMapper;
    @Autowired
    private ApiMeetInputViewToMeetMapper meetMapper;

    @Override
    public List<ApiMeetOutputView> findAll() {
        final List<Meet> meets = meetService.findAll();

        return meets.stream()
                .map(e -> meetOutputViewMapper.map(e))
                .collect(Collectors.toList());
    }

    @Override
    public ApiMeetOutputView findOne(Integer entityId) {
        final Meet meet = meetService.findOne(entityId);

        if (meet == null) {
            throw new ApiResourceNotFoundException();
        }
        return meetOutputViewMapper.map(meet);
    }

    @Override
    public ApiMeetOutputView create(ApiMeetInputView viewObject) {
        final Meet meet = meetMapper.map(viewObject);
        final Meet newMeet = meetService.save(meet);

        return meetOutputViewMapper.map(newMeet);
    }

    @Override
    public ApiMeetOutputView update(Integer entityId, ApiMeetInputView viewObject) {
        final Meet meet = meetService.findOne(entityId);

        if (meet == null) {
            throw new ApiResourceNotFoundException();
        }

        final Meet updatedMeet = meetService.save(meetMapper.map(viewObject));
        return meetOutputViewMapper.map(updatedMeet);
    }

    /**
     * Returns all meets that are registered in a given competition.
     *
     * @param competitionId The competition ID
     * @return The list of meets
     */
    public List<ApiMeetOutputView> getAllByCompetitionId(Integer competitionId) {
        final List<Meet> meets = meetService.findAllByCompetitionId(competitionId);

        return meets.stream()
                .map(e -> meetOutputViewMapper.map(e))
                .collect(Collectors.toList());
    }

    /**
     * Shows all meet programs that a given swimmer has participated.
     *
     * @param swimmerId The swimmer ID
     * @return A list of meet output views
     */
    public List<ApiMeetOutputView> getAllBySwimmerId(Integer swimmerId) {
        final List<Meet> meets = meetService.findAllBySwimmerId(swimmerId);

        return meets.stream()
                .map(e -> meetOutputViewMapper.map(e))
                .collect(Collectors.toList());
    }
}