package com.zwemmen.psv;

import com.zwemmen.psv.event.competition.BaseCompetitionService;
import com.zwemmen.psv.event.competition.Competition;
import com.zwemmen.psv.meet.BaseMeetService;
import com.zwemmen.psv.meet.Meet;
import com.zwemmen.psv.result.BaseResultService;
import com.zwemmen.psv.result.Result;
import com.zwemmen.psv.swimmer.BaseSwimmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This component handles the registration and drop out of swimmers to meets.
 *
 * @author afernandez
 */
@Component
public class MeetRegistrator {

    @Autowired
    private BaseMeetService meetService;
    @Autowired
    private BaseSwimmerService swimmerService;
    @Autowired
    private BaseCompetitionService competitionService;
    @Autowired
    private BaseResultService resultService;

    /**
     * Register a swimmer to a meet in the result entity meaning the swimmer joined the
     * specific meet in a competition.
     *
     * @param meetId The meet ID
     * @param swimmerId The swimmer ID
     * @param competitionId The competition ID
     * @return The result output view with its registered swimmer and meet
     */
    public Meet registerToMeet(Integer meetId, Integer swimmerId, Integer competitionId) {
        final Meet meet = meetService.findOne(meetId);

        validateCompetition(meet);
        validateMeet(competitionId, meet);

        final Result result = new Result.Builder()
                .meet(meet)
                .swimmer(swimmerService.findOne(swimmerId))
                .build();

        resultService.save(result);
        return meet;
    }

    /**
     * Creates and adds a given meet to a specific competition.
     *
     * The meet program is created here as opposed to its own function because creation and registration
     * of a meet to a competition are done in a single action. It does not make sense to create meets without
     * being directly attached to a competition.
     *
     * @param competitionId The competition ID
     * @param meet The meet entity
     * @return The given competition to register the meet to
     */
    public Meet addMeet(Integer competitionId, Meet meet) {
        final Competition competition = competitionService.findOne(competitionId);
        final Meet newMeet = meetService.save(meet);

        competition.addMeet(newMeet);
        competitionService.save(competition);

        return newMeet;
    }

    private void validateCompetition(Meet meet) {
        if (meet.getCompetition() == null) {
            throw new IllegalStateException(String.format("The meet [%s] is not registered to any competition.",
                    meet.getId()));
        }
    }

    private void validateMeet(int competitionId, Meet meet) {
        final Competition competition = competitionService.findOne(competitionId);

        competition.getMeets().stream()
                .filter(m -> m.getId().equals(meet.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("The meet [%s] is not part of the competition.", meet.getId())));
    }
}