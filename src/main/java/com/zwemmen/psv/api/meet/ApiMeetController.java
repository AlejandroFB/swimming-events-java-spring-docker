package com.zwemmen.psv.api.meet;

import com.zwemmen.psv.api.generic.ApiBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Endpoints to perform CRUD operations and additional actions on meet objects.
 *
 * @author afernandez
 */
@RestController
@RequestMapping(value = "user/meets")
public class ApiMeetController extends ApiBaseController<ApiMeetInputView, ApiMeetOutputView, Integer, ApiMeetService> {

    @Autowired
    private ApiMeetService apiMeetService;

    @Override
    protected ApiMeetService getService() {
        return apiMeetService;
    }

    @Override
    public ApiMeetOutputView create(ApiMeetInputView viewObject) {
        throw new UnsupportedOperationException("Meets are created directly through competitions.");
    }

    /**
     * Shows all meet programs that are registered to a given competition.
     *
     * @param competitionId The competition ID
     * @return A list of meet output views
     */
    @GetMapping("/competition/{competitionId}")
    public List<ApiMeetOutputView> getAllByCompetition(@PathVariable("competitionId") Integer competitionId) {
        return apiMeetService.getAllByCompetitionId(competitionId);
    }

    /**
     * Shows all meet programs that a given swimmer has participated.
     *
     * @param swimmerId The swimmer ID
     * @return A list of meet output views
     */
    @GetMapping("/swimmer/{swimmerId}")
    public List<ApiMeetOutputView> getAllBySwimmer(@PathVariable("swimmerId") Integer swimmerId) {
        return apiMeetService.getAllBySwimmerId(swimmerId);
    }
}