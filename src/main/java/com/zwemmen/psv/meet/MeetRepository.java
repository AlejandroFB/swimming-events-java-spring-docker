package com.zwemmen.psv.meet;

import com.zwemmen.psv.generic.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Meet repository.
 *
 * @author afernandez
 */
interface MeetRepository extends BaseRepository<Meet, Integer> {

    /**
     * Find all meets part of a given competition.
     *
     * @param competitionId The competition number
     * @return A list of meet entities
     */
    List<Meet> findAllByCompetitionId(Integer competitionId);

    /**
     * Find all meets that a given swimmer has participated.
     *
     * @param swimmerId The swimmer ID
     * @return List of meet entities
     */
    @Query(value = "select m from Meet m join m.results r where r.swimmer.id = :swimmerId")
    List<Meet> findAllBySwimmerId(@Param("swimmerId") Integer swimmerId);

    /**
     * Find whether a given swimmer has participated to a specific meet.
     *
     * @param swimmerId The swimmer ID
     * @param meetId The meet ID
     * @return The meet entity
     */
    @Query(value = "select m from Meet m join m.results r where r.swimmer.id = :swimmerId and r.meet.id = :meetId")
    Meet findMeetBySwimmerId(@Param("swimmerId") Integer swimmerId, @Param("meetId") Integer meetId);
}