package com.zwemmen.psv.swimmer;

import com.zwemmen.psv.generic.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Swimmer repository.
 *
 * @author afernandez
 */
interface SwimmerRepository extends BaseRepository<Swimmer, Integer> {

    /**
     * Retrieves all swimmers registered to a given competition.
     *
     * @param competitionId The competition ID
     * @return List of swimmer entities
     */
    @Query(value = "select distinct s from Swimmer s " +
            "join s.results r " +
            "join r.meet m " +
            "join m.competition c " +
            "where s.id = r.swimmer.id and m.id = r.meet.id and c.id = :competitionId and r.approvedByCoach = true")
    List<Swimmer> findAllByCompetitionId(@Param("competitionId") Integer competitionId);

    /**
     * Retrieves all swimmers registered to a given meet program.
     *
     * @param meetId The meet ID
     * @return List of swimmer entities
     */
    @Query(value = "select s from Swimmer s join s.results r where r.meet.id = :meetId")
    List<Swimmer> findAllByMeetId(@Param("meetId") Integer meetId);

    /**
     * Retrieves the information of the swimmer that is registered to the given competition (through one or more meets).
     * It can be used to validate if a specific swimmer is registered to a specific competition.
     *
     * @param competitionId The competition ID
     * @param swimmerId The swimmer ID
     * @return The swimmer entity
     */
    @Query(value = "select distinct s from Swimmer s " +
            "join s.results r " +
            "join r.meet m " +
            "join m.competition c " +
            "where s.id = :swimmerId and m.id = r.meet.id and c.id = :competitionId and r.approvedByCoach = true")
    Swimmer findSwimmerInCompetition(@Param("competitionId") Integer competitionId, @Param("swimmerId") Integer swimmerId);

    /**
     * Retrieves a swimmer given his user name.
     *
     * @param userName The user name of the swimmer.
     * @return The swimmer entity
     */
    Swimmer findByUserName(String userName);
}