package com.zwemmen.psv.event.competition;

import com.zwemmen.psv.generic.BaseRepository;
import com.zwemmen.psv.generic.BaseService;

import java.io.Serializable;
import java.util.List;

/**
 * Base competition service that includes the abstract definition of specific actions and adds functionality
 * to the contract methods.
 *
 * @author afernandez
 */
public abstract class BaseCompetitionService<ID extends Serializable, R extends BaseRepository<Competition, ID>>
        extends BaseService<Competition, ID, R> {

    public BaseCompetitionService(R repository) {
        super(repository);
    }

    @Override
    public Competition findOne(ID id) {
        return repository.findOne(id);
    }

    @Override
    public List<Competition> findAll() {
        return repository.findAll();
    }

    @Override
    public Competition save(Competition competition) {
        return repository.save(competition);
    }

    @Override
    public void delete(Competition competition) {
        repository.delete(competition);
    }

    public abstract List<Competition> findAllBySwimmerId(Integer swimmerId);

    public abstract List<Competition> findOldCompetitionsBySwimmerId(Integer swimmerId);
}