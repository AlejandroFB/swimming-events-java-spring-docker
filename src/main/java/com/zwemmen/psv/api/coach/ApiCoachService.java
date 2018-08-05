package com.zwemmen.psv.api.coach;

import com.zwemmen.psv.api.coach.mapper.ApiCoachInputViewToCoachMapper;
import com.zwemmen.psv.api.coach.mapper.CoachToApiCoachOutputViewMapper;
import com.zwemmen.psv.api.exception.ApiResourceNotFoundException;
import com.zwemmen.psv.api.generic.ApiService;
import com.zwemmen.psv.coach.BaseCoachService;
import com.zwemmen.psv.coach.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service layer of coach API module. Makes use of coach related services
 * to get or update data.
 *
 * @author afernandez
 */
@Service
public class ApiCoachService implements ApiService<ApiCoachInputView, ApiCoachOutputView, Integer> {

    @Autowired
    private BaseCoachService coachService;
    @Autowired
    private CoachToApiCoachOutputViewMapper coachOutputViewMapper;
    @Autowired
    private ApiCoachInputViewToCoachMapper coachMapper;

    @Override
    public List<ApiCoachOutputView> findAll() {
        final List<Coach> coaches = coachService.findAll();

        return coaches.stream()
                .map(e -> coachOutputViewMapper.map(e))
                .collect(Collectors.toList());
    }

    @Override
    public ApiCoachOutputView findOne(Integer entityId) {
        final Coach coach = coachService.findOne(entityId);

        if (coach == null) {
            throw new ApiResourceNotFoundException();
        }
        return coachOutputViewMapper.map(coach);
    }

    @Override
    public ApiCoachOutputView create(ApiCoachInputView viewObject) {
        final Coach coach = coachMapper.map(viewObject);
        final Coach newCoach = coachService.save(coach);

        return coachOutputViewMapper.map(newCoach);
    }

    @Override
    public ApiCoachOutputView update(Integer entityId, ApiCoachInputView viewObject) {
        final Coach coach = coachService.findOne(entityId);

        if (coach == null) {
            throw new ApiResourceNotFoundException();
        }

        final Coach updatedCoach = coachService.save(coachMapper.map(viewObject));
        return coachOutputViewMapper.map(updatedCoach);
    }
}