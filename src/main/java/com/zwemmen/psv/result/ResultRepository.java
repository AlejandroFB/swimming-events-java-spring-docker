package com.zwemmen.psv.result;

import com.zwemmen.psv.generic.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Meet repository.
 *
 * @author afernandez
 */
interface ResultRepository extends BaseRepository<Result, Integer> {

    @Query(value = "select r from Result r where r.meet.id = :meetId and r.swimmer.id = :swimmerId")
    Result findByMeetIdAndSwimmerId(@Param("meetId") Integer meetId, @Param("swimmerId") Integer swimmerId);
}