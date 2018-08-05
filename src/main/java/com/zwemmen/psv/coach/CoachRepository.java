package com.zwemmen.psv.coach;

import com.zwemmen.psv.generic.BaseRepository;

/**
 * Coach repository.
 *
 * @author afernandez
 */
interface CoachRepository extends BaseRepository<Coach, Integer> {

    /**
     * Retrieves a coach given his email address.
     *
     * @param emailAddress The email address of the coach.
     * @return The coach entity
     */
    Coach findByEmailAddress(String emailAddress);
}