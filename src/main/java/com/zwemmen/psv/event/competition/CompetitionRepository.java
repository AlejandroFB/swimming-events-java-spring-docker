package com.zwemmen.psv.event.competition;

import com.zwemmen.psv.generic.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * Competition repository.
 *
 * @author afernandez
 */
interface CompetitionRepository extends BaseRepository<Competition, Integer> {

    /**
     * Retrieves all competitions that a swimmer is register to.
     *
     * @param swimmerId The swimmer ID
     * @return The list of competitions
     */
    @Query(value = "select distinct c from Competition c " +
            "join c.meets m " +
            "join m.results r " +
            "where r.swimmer.id = :swimmerId and r.approvedByCoach = true")
    List<Competition> findAllBySwimmerId(@Param("swimmerId") Integer swimmerId);

    /**
     * Retrieves all competitions that a swimmer was register in the past, as a historic view.
     *
     * @param swimmerId The swimmer ID
     * @return The list of competitions
     */
    @Query(value = "select distinct c from Competition c " +
            "join c.meets m " +
            "join m.results r " +
            "where r.swimmer.id = :swimmerId and r.approvedByCoach = true and c.endDate < :date")
    List<Competition> findOldCompetitionsBySwimmerId(@Param("swimmerId") Integer swimmerId, @Param("date") LocalDate date);
}