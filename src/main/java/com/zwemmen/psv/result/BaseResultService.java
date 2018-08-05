package com.zwemmen.psv.result;

import com.zwemmen.psv.generic.BaseRepository;
import com.zwemmen.psv.generic.BaseService;

import java.io.Serializable;
import java.util.List;

/**
 * Base result service that includes the abstract definition of specific actions and adds functionality
 * to the contract methods.
 *
 * @author afernandez
 */
public abstract class BaseResultService<ID extends Serializable, R extends BaseRepository<Result, ID>>
        extends BaseService<Result, ID, R> {

    public BaseResultService(R repository) {
        super(repository);
    }

    @Override
    public Result findOne(ID id) {
        return repository.findOne(id);
    }

    @Override
    public List<Result> findAll() {
        return repository.findAll();
    }

    @Override
    public Result save(Result result) {
        return repository.save(result);
    }

    @Override
    public void delete(Result result) {
        repository.delete(result);
    }

    public abstract Result findByMeetIdAndSwimmerId(Integer meetId, Integer swimmerId);
}