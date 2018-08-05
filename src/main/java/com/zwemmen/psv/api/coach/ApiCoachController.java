package com.zwemmen.psv.api.coach;

import com.zwemmen.psv.api.generic.ApiBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoints to perform CRUD operations and additional actions on competition objects.
 *
 * @author afernandez
 */
@RestController
@RequestMapping(value = "management/coaches")
public class ApiCoachController extends ApiBaseController<ApiCoachInputView, ApiCoachOutputView,
        Integer, ApiCoachService> {

    @Autowired
    private ApiCoachService apiCoachService;

    @Override
    protected ApiCoachService getService() {
        return apiCoachService;
    }
}