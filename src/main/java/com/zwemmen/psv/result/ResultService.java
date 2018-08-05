package com.zwemmen.psv.result;

import org.springframework.stereotype.Service;

/**
 * Result service.
 *
 * @author afernandez
 */
@Service
public class ResultService extends BaseResultService<Integer, ResultRepository> {

    public ResultService(ResultRepository repository) {
        super(repository);
    }

    public Result findByMeetIdAndSwimmerId(Integer meetId, Integer swimmerId) {
        return repository.findByMeetIdAndSwimmerId(meetId, swimmerId);
    }
}