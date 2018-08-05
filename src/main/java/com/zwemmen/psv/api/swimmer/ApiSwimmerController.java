package com.zwemmen.psv.api.swimmer;

import com.zwemmen.psv.api.generic.ApiBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Endpoints to perform CRUD operations and additional actions on swimmer objects.
 *
 * @author afernandez
 */
@RestController
@RequestMapping(value = "user/swimmers")
public class ApiSwimmerController extends ApiBaseController<ApiSwimmerInputView, ApiSwimmerOutputView, Integer, ApiSwimmerService> {

    @Autowired
    private ApiSwimmerService apiSwimmerService;

    @Override
    protected ApiSwimmerService getService() {
        return apiSwimmerService;
    }

    /**
     * Shows all swimmers that are registered to a given competition.
     *
     * @param competitionId The competition ID
     * @return A list of swimmer output views
     */
    @GetMapping("/competition/{competitionId}")
    public List<ApiSwimmerOutputView> getAllByCompetition(@PathVariable("competitionId") Integer competitionId) {
        return apiSwimmerService.getAllByCompetitionId(competitionId);
    }

    /**
     * Shows all swimmers that are registered to a given meet program.
     *
     * @param meetId The meet ID
     * @return A list of swimmer output views
     */
    @GetMapping("/meet/{meetId}")
    public List<ApiSwimmerOutputView> getAllByMeet(@PathVariable("meetId") Integer meetId) {
        return apiSwimmerService.getAllByMeetId(meetId);
    }
}