package com.zwemmen.psv.api.competition;

import com.zwemmen.psv.MeetRegistrator;
import com.zwemmen.psv.api.competition.mapper.ApiCompetitionInputViewToCompetitionMapper;
import com.zwemmen.psv.api.competition.mapper.CompetitionToApiCompetitionOutputViewMapper;
import com.zwemmen.psv.api.exception.ApiResourceNotFoundException;
import com.zwemmen.psv.api.generic.ApiService;
import com.zwemmen.psv.api.meet.ApiMeetInputView;
import com.zwemmen.psv.api.meet.mapper.ApiMeetInputViewToMeetMapper;
import com.zwemmen.psv.event.competition.BaseCompetitionService;
import com.zwemmen.psv.event.competition.Competition;
import com.zwemmen.psv.meet.BaseMeetService;
import com.zwemmen.psv.meet.Meet;
import com.zwemmen.psv.result.BaseResultService;
import com.zwemmen.psv.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service layer of competition API module. Makes use of competition related services
 * to get or update data.
 *
 * @author afernandez
 */
@Service
public class ApiCompetitionService implements ApiService<ApiCompetitionInputView, ApiCompetitionOutputView, Integer> {

    @Autowired
    private BaseCompetitionService competitionService;
    @Autowired
    private BaseMeetService meetService;
    @Autowired
    private BaseResultService resultService;
    @Autowired
    private MeetRegistrator meetRegistrator;
    @Autowired
    private CompetitionToApiCompetitionOutputViewMapper competitionOutputViewMapper;
    @Autowired
    private ApiCompetitionInputViewToCompetitionMapper competitionMapper;
    @Autowired
    private ApiMeetInputViewToMeetMapper meetMapper;

    @Override
    public List<ApiCompetitionOutputView> findAll() {
        final List<Competition> competitions = competitionService.findAll();

        return competitions.stream()
                .filter(competition -> competition.getEndDate().isAfter(LocalDate.now()))
                .map(e -> competitionOutputViewMapper.map(e))
                .collect(Collectors.toList());
    }

    @Override
    public ApiCompetitionOutputView findOne(Integer entityId) {
        final Competition competition = competitionService.findOne(entityId);

        if (competition == null) {
            throw new ApiResourceNotFoundException();
        }
        return competitionOutputViewMapper.map(competition);
    }

    @Override
    public ApiCompetitionOutputView create(ApiCompetitionInputView viewObject) {
        final Competition competition = competitionMapper.map(viewObject);
        final Competition newCompetition = competitionService.save(competition);

        return competitionOutputViewMapper.map(newCompetition);
    }

    @Override
    public ApiCompetitionOutputView update(Integer entityId, ApiCompetitionInputView viewObject) {
        final Competition competition = competitionService.findOne(entityId);

        if (competition == null) {
            throw new ApiResourceNotFoundException();
        }

        final Competition updatedCompetition = competitionService.save(competitionMapper.map(viewObject));
        return competitionOutputViewMapper.map(updatedCompetition);
    }

    /**
     * Retrieves a list with all competitions a given swimmer is registered to participate.
     *
     * @param swimmerId The swimmer ID
     * @return A list of competition output views
     */
    public List<ApiCompetitionOutputView> getAllBySwimmerId(Integer swimmerId) {
        final List<Competition> competitions = competitionService.findAllBySwimmerId(swimmerId);

        return competitions.stream()
                .map(e -> competitionOutputViewMapper.map(e))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list with all competitions a given swimmer participated in the past, as a historic view.
     *
     * @param swimmerId The swimmer ID
     * @return A list of competition output views
     */
    public List<ApiCompetitionOutputView> getOldCompetitionsBySwimmerId(Integer swimmerId) {
        final List<Competition> competitions = competitionService.findOldCompetitionsBySwimmerId(swimmerId);

        return competitions.stream()
                .map(e -> competitionOutputViewMapper.map(e))
                .collect(Collectors.toList());
    }

    /**
     * Registers a swimmer to a given competition including her meets to participate. The process is not approved
     * by coaches because this function is triggered when a swimmer registers to them.
     *
     * @param competitionId The competition ID
     * @param swimmerId The swimmer ID
     * @param meetIds List with all the meet IDs the swimmers is going to register
     * @return The given competition output view
     */
    public ApiCompetitionOutputView registerSwimmer(Integer competitionId, Integer swimmerId, List<Integer> meetIds) {
        final Competition competition = competitionService.findOne(competitionId);;

        meetIds.stream()
                .filter(id -> meetService.findMeetBySwimmerId(swimmerId, id) == null)
                .forEach(id -> meetRegistrator.registerToMeet(id, swimmerId, competitionId));

        return competitionOutputViewMapper.map(competition);
    }

    /**
     * Registers a swimmer to a given competition including her meets to participate. Approves the meets.
     *
     * @param competitionId The competition ID
     * @param swimmerId The swimmer ID
     * @param meetIds List with all the meet IDs the swimmers is going to register
     * @return The given competition output view
     */
    public ApiCompetitionOutputView registerSwimmerByCoach(Integer competitionId, Integer swimmerId, List<Integer> meetIds) {
        final Competition competition = competitionService.findOne(competitionId);;

        meetIds.forEach(meetId -> processRegistration(swimmerId, meetId, competitionId));

        return competitionOutputViewMapper.map(competition);
    }

    /**
     * Adds a new meet to a given competition. It uses the competition registrator that automatically creates
     * the specified meet and then attaches it to the given competition.
     *
     * @param competitionId The competition ID
     * @param meetView The meet view object
     * @return The given competition output view
     */
    public ApiCompetitionOutputView addMeet(Integer competitionId, ApiMeetInputView meetView) {
        final Meet meet = meetMapper.map(meetView);
        meetRegistrator.addMeet(competitionId, meet);

        return competitionOutputViewMapper.map(competitionService.findOne(competitionId));
    }

    private void processRegistration(Integer swimmerId, Integer meetId, Integer competitionId) {
        if (meetService.findMeetBySwimmerId(swimmerId, meetId) == null) {
            meetRegistrator.registerToMeet(meetId, swimmerId, competitionId);
        }

        Result result = resultService.findByMeetIdAndSwimmerId(meetId, swimmerId);
        result.approveSwimmerInMeet();

        resultService.save(result);
    }
}