package com.zwemmen.psv.coach;

import com.zwemmen.psv.generic.BaseRepository;
import com.zwemmen.psv.generic.BaseService;

import java.io.Serializable;
import java.util.List;

/**
 * Base coach service that includes the abstract definition of specific actions and adds functionality
 * to the contract methods.
 *
 * @author afernandez
 */
public abstract class BaseCoachService<ID extends Serializable, R extends BaseRepository<Coach, ID>>
        extends BaseService<Coach, ID, R> {

    public BaseCoachService(R repository) {
        super(repository);
    }

    @Override
    public Coach findOne(ID id) {
        return repository.findOne(id);
    }

    @Override
    public List<Coach> findAll() {
        return repository.findAll();
    }

    @Override
    public Coach save(Coach coach) {
        return repository.save(coach);
    }

    @Override
    public void delete(Coach coach) {
        repository.delete(coach);
    }

    public abstract Coach findByEmailAddress(String emailAddress);
}