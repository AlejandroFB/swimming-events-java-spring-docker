package com.zwemmen.psv.meet;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Meet service.
 *
 * @author afernandez
 */
@Service
public class MeetService extends BaseMeetService<Integer, MeetRepository> {

    public MeetService(MeetRepository repository) {
        super(repository);
    }

    /**
     * Retrieves all meets registered to a given competition.
     *
     * @param competitionId The competition ID
     * @return List of meet entities
     */
    public List<Meet> findAllByCompetitionId(Integer competitionId) {
        return repository.findAllByCompetitionId(competitionId);
    }

    /**
     * Retrieves all meets that a given swimmer has participated.
     *
     * @param swimmerId The swimmer ID
     * @return List of meet entities
     */
    public List<Meet> findAllBySwimmerId(Integer swimmerId) {
        return repository.findAllBySwimmerId(swimmerId);
    }

    /**
     * Find whether a given swimmer has participated to a specific meet.
     *
     * @param swimmerId The swimmer ID
     * @param meetId The meet ID
     * @return The meet entity
     */
    public Meet findMeetBySwimmerId(Integer swimmerId, Integer meetId) {
        return repository.findMeetBySwimmerId(swimmerId, meetId);
    }
}