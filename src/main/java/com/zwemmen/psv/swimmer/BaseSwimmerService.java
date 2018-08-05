package com.zwemmen.psv.swimmer;

import com.zwemmen.psv.generic.BaseService;
import com.zwemmen.psv.generic.BaseRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Base swimmer service that includes the abstract definition of specific actions and adds functionality
 * to the contract methods.
 *
 * @author afernandez
 */
public abstract class BaseSwimmerService<ID extends Serializable, R extends BaseRepository<Swimmer, ID>>
        extends BaseService<Swimmer, ID, R> {

    public BaseSwimmerService(R repository) {
        super(repository);
    }

    @Override
    public Swimmer findOne(ID id) {
        return repository.findOne(id);
    }

    @Override
    public List<Swimmer> findAll() {
        return repository.findAll();
    }

    @Override
    public Swimmer save(Swimmer swimmer) {
        return repository.save(swimmer);
    }

    @Override
    public void delete(Swimmer swimmer) {
        repository.delete(swimmer);
    }

    public abstract List<Swimmer> findAllByCompetitionId(Integer competitionId);

    public abstract List<Swimmer> findAllByMeetId(Integer meetId);

    public abstract Swimmer findByUserName(String userName);
}