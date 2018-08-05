package com.zwemmen.psv.event.competition;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Competition service.
 *
 * @author afernandez
 */
@Service
public class CompetitionService extends BaseCompetitionService<Integer, CompetitionRepository> {

    public CompetitionService(CompetitionRepository repository) {
        super(repository);
    }

    /**
     * Retrieves all competitions that a swimmer is register to.
     *
     * @param swimmerId The swimmer ID
     * @return The list of competitions
     */
    public List<Competition> findAllBySwimmerId(Integer swimmerId) {
        return repository.findAllBySwimmerId(swimmerId);
    }

    /**
     * Retrieves all competitions that a swimmer registered and are in the past, as a history view.
     *
     * @param swimmerId The swimmer ID
     * @return The list of competitions
     */
    public List<Competition> findOldCompetitionsBySwimmerId(Integer swimmerId) {
        return repository.findOldCompetitionsBySwimmerId(swimmerId, LocalDate.now());
    }
 }