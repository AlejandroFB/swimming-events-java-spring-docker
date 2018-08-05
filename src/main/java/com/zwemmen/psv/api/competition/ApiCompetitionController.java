package com.zwemmen.psv.api.competition;

import com.zwemmen.psv.api.generic.ApiBaseController;
import com.zwemmen.psv.api.meet.ApiMeetInputView;
import com.zwemmen.psv.api.security.authentication.swimmer.SwimmerUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints to perform CRUD operations and additional actions on competition objects.
 *
 * @author afernandez
 */
@RestController
@RequestMapping(value = "user/competitions")
public class ApiCompetitionController extends ApiBaseController<ApiCompetitionInputView, ApiCompetitionOutputView,
        Integer, ApiCompetitionService> {

    @Autowired
    private ApiCompetitionService apiCompetitionService;

    @Override
    protected ApiCompetitionService getService() {
        return apiCompetitionService;
    }

    /**
     * Shows all competitions that a given swimmer is registered to.
     *
     * @param swimmerId The swimmer ID
     * @return A list of competition output views
     */
    @GetMapping("/swimmer/{swimmerId}")
    public List<ApiCompetitionOutputView> getAllBySwimmer(@PathVariable("swimmerId") Integer swimmerId) {
        return apiCompetitionService.getAllBySwimmerId(swimmerId);
    }

    /**
     * Shows all competitions that a given swimmer registered in the past, as a historic view.
     *
     * @param swimmerId The swimmer ID
     * @return A list of competition output views
     */
    @GetMapping("/past/swimmer/{swimmerId}")
    public List<ApiCompetitionOutputView> getOldCompetitionsBySwimmerId(@PathVariable("swimmerId") Integer swimmerId) {
        return apiCompetitionService.getOldCompetitionsBySwimmerId(swimmerId);
    }

    /**
     * Registers the logged in swimmer to a specific competition, including its selected meets.
     *
     * @param swimmer The logged in swimmer
     * @param competitionId The competition ID
     * @param meetIdentifiers A wrapper including a list of meet ids
     * @return The competition the given swimmer has been registered
     */
    @PutMapping("/{competitionId}/register")
    public ApiCompetitionOutputView register(@AuthenticationPrincipal SwimmerUser swimmer,
                                             @PathVariable("competitionId") Integer competitionId,
                                             @RequestBody MeetIdentifiers meetIdentifiers) {
        return apiCompetitionService.registerSwimmer(competitionId, swimmer.getSwimmer().getId(), meetIdentifiers.getMeetIds());
    }

    /**
     * Creates a meet program and registers it to a given competition. Meets are created with
     * their associated competition.
     *
     * @param competitionId The competition ID
     * @param apiMeetInputView The meet input view with data needed to create the meet
     * @return The competition where the meet has just been registered
     */
    @PutMapping("/{competitionId}/meet/register")
    public ApiCompetitionOutputView addMeet(@PathVariable("competitionId") Integer competitionId,
                                            @RequestBody ApiMeetInputView apiMeetInputView) {
        return apiCompetitionService.addMeet(competitionId, apiMeetInputView);
    }
}