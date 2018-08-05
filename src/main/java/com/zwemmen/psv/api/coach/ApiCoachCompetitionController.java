package com.zwemmen.psv.api.coach;

import com.zwemmen.psv.api.competition.ApiCompetitionInputView;
import com.zwemmen.psv.api.competition.ApiCompetitionOutputView;
import com.zwemmen.psv.api.competition.ApiCompetitionService;
import com.zwemmen.psv.api.generic.ApiBaseController;
import com.zwemmen.psv.api.meet.ApiMeetOutputView;
import com.zwemmen.psv.api.meet.ApiMeetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints to perform CRUD operations and additional actions on competition objects.
 *
 * @author afernandez
 */
@RestController
@RequestMapping(value = "management/competitions")
public class ApiCoachCompetitionController extends ApiBaseController<ApiCompetitionInputView, ApiCompetitionOutputView,
        Integer, ApiCompetitionService> {

    @Autowired
    private ApiCompetitionService apiCompetitionService;
    @Autowired
    private ApiMeetService apiMeetService;

    @Override
    protected ApiCompetitionService getService() {
        return apiCompetitionService;
    }

    /**
     * Registers the logged in swimmer to a specific competition and validates its meets. Validates the meets in case
     * the swimmer was already registered.
     *
     * @param competitionId The competition ID
     * @param swimmerId The swimmer to register
     * @param meetIdentifiers A wrapper including a list of meet ids
     * @return The competition the given swimmer has been registered
     */
    @PutMapping("/{competitionId}/register/swimmer/{swimmerId}")
    public ApiCompetitionOutputView register(@PathVariable("competitionId") Integer competitionId,
                                             @PathVariable("swimmerId") Integer swimmerId,
                                             @RequestBody MeetIdentifiers meetIdentifiers) {
        return apiCompetitionService.registerSwimmerByCoach(competitionId, swimmerId, meetIdentifiers.getMeetIds());
    }

    /**
     * Shows all meet programs that are registered to a given competition.
     *
     * @param competitionId The competition ID
     * @return A list of meet output views
     */
    @GetMapping("/{competitionId}/meets")
    public List<ApiMeetOutputView> getAllByCompetition(@PathVariable("competitionId") Integer competitionId) {
        return apiMeetService.getAllByCompetitionId(competitionId);
    }
}