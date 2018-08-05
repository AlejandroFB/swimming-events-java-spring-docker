package com.zwemmen.psv.meet;

import com.zwemmen.psv.generic.BaseRepository;
import com.zwemmen.psv.generic.BaseService;

import java.io.Serializable;
import java.util.List;

/**
 * Base meet service that includes the abstract definition of specific actions and adds functionality
 * to the contract methods.
 *
 * @author afernandez
 */
public abstract class BaseMeetService<ID extends Serializable, R extends BaseRepository<Meet, ID>>
        extends BaseService<Meet, ID, R> {

    public BaseMeetService(R repository) {
        super(repository);
    }

    @Override
    public Meet findOne(ID id) {
        return repository.findOne(id);
    }

    @Override
    public List<Meet> findAll() {
        return repository.findAll();
    }

    @Override
    public Meet save(Meet meet) {
        return repository.save(meet);
    }

    @Override
    public void delete(Meet meet) {
        repository.delete(meet);
    }

    public abstract List<Meet> findAllByCompetitionId(Integer competitionId);

    public abstract List<Meet> findAllBySwimmerId(Integer swimmerId);

    public abstract Meet findMeetBySwimmerId(Integer swimmerId, Integer meetId);
}