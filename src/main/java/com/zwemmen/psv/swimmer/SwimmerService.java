package com.zwemmen.psv.swimmer;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Swimmer service.
 *
 * @author afernandez
 */
@Service
public class SwimmerService extends BaseSwimmerService<Integer, SwimmerRepository> {

    public SwimmerService(SwimmerRepository repository) {
        super(repository);
    }

    /**
     * Retrieves all swimmers registered to a given competition.
     *
     * @param competitionId The competition ID
     * @return List of swimmer entities
     */
    public List<Swimmer> findAllByCompetitionId(Integer competitionId) {
        return repository.findAllByCompetitionId(competitionId);
    }

    /**
     * Retrieves all swimmers registered to a given meet program.
     *
     * @param meetId The meet ID
     * @return List of swimmer entities
     */
    public List<Swimmer> findAllByMeetId(Integer meetId) {
        return repository.findAllByMeetId(meetId);
    }

    /**
     * Retrieves the information of the swimmer that is registered to the given competition (through one or more meets).
     * It can be used to validate if a specific swimmer is registered to a specific competition.
     *
     * @param competitionId The competition ID
     * @param swimmerId The swimmer ID
     * @return The swimmer entity
     */
    public Swimmer findSwimmerInCompetition(Integer competitionId, Integer swimmerId) {
        return repository.findSwimmerInCompetition(competitionId, swimmerId);
    }

    /**
     * Retrieves a swimmer given his user name.
     *
     * @param userName The user name of the swimmer.
     * @return The swimmer entity
     */
    public Swimmer findByUserName(String userName) {
        return repository.findByUserName(userName);
    }
}