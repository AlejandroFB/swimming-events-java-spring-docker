package com.zwemmen.psv.api.swimmer;

import com.zwemmen.psv.api.exception.ApiResourceNotFoundException;
import com.zwemmen.psv.api.generic.ApiService;
import com.zwemmen.psv.api.swimmer.mapper.ApiSwimmerInputViewToSwimmerMapper;
import com.zwemmen.psv.api.swimmer.mapper.SwimmerToApiSwimmerOutputViewMapper;
import com.zwemmen.psv.swimmer.BaseSwimmerService;
import com.zwemmen.psv.swimmer.Swimmer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service layer of swimmers API module. Makes use of swimmer related services
 * to get or update data.
 *
 * @author afernandez
 */
@Service
public class ApiSwimmerService implements ApiService<ApiSwimmerInputView, ApiSwimmerOutputView, Integer> {

    @Autowired
    private BaseSwimmerService swimmerService;
    @Autowired
    private SwimmerToApiSwimmerOutputViewMapper swimmerOutputViewMapper;
    @Autowired
    private ApiSwimmerInputViewToSwimmerMapper swimmerMapper;

    @Override
    public List<ApiSwimmerOutputView> findAll() {
        final List<Swimmer> swimmers = swimmerService.findAll();

        return swimmers.stream()
                .map(e -> swimmerOutputViewMapper.map(e))
                .collect(Collectors.toList());
    }

    @Override
    public ApiSwimmerOutputView findOne(Integer entityId) {
        final Swimmer swimmer = swimmerService.findOne(entityId);

        if (swimmer == null) {
            throw new ApiResourceNotFoundException();
        }
        return swimmerOutputViewMapper.map(swimmer);
    }

    @Override
    public ApiSwimmerOutputView create(ApiSwimmerInputView viewObject) {
        final Swimmer swimmer = swimmerMapper.map(viewObject);
        final Swimmer newSwimmer = swimmerService.save(swimmer);

        return swimmerOutputViewMapper.map(newSwimmer);
    }

    @Override
    public ApiSwimmerOutputView update(Integer entityId, ApiSwimmerInputView viewObject) {
        final Swimmer swimmer = swimmerService.findOne(entityId);

        if (swimmer == null) {
            throw new ApiResourceNotFoundException();
        }

        final Swimmer updatedSwimmer = swimmerService.save(swimmerMapper.map(viewObject));
        return swimmerOutputViewMapper.map(updatedSwimmer);
    }

    /**
     * Retrieves a list with all swimmers registered to a given competition.
     *
     * @param competitionId The competition ID
     * @return A list of swimmer output views
     */
    public List<ApiSwimmerOutputView> getAllByCompetitionId(Integer competitionId) {
        final List<Swimmer> swimmers = swimmerService.findAllByCompetitionId(competitionId);

        return swimmers.stream()
                .map(e -> swimmerOutputViewMapper.map(e))
                .collect(Collectors.toList());
    }

    /**
     * Shows all swimmers that are registered to a given meet program.
     *
     * @param meetId The meet ID
     * @return A list of swimmer output views
     */
    public List<ApiSwimmerOutputView> getAllByMeetId(Integer meetId) {
        final List<Swimmer> swimmers = swimmerService.findAllByMeetId(meetId);

        return swimmers.stream()
                .map(e -> swimmerOutputViewMapper.map(e))
                .collect(Collectors.toList());
    }
}