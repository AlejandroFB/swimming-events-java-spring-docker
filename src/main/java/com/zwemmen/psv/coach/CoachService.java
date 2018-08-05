package com.zwemmen.psv.coach;

import org.springframework.stereotype.Service;

/**
 * Coach service.
 *
 * @author afernandez
 */
@Service
public class CoachService extends BaseCoachService<Integer, CoachRepository> {

    public CoachService(CoachRepository repository) {
        super(repository);
    }

    /**
     * Retrieves a coach given his email address.
     *
     * @param emailAddress The email address of the coach.
     * @return The coach entity
     */
    public Coach findByEmailAddress(String emailAddress) {
        return repository.findByEmailAddress(emailAddress);
    }
}