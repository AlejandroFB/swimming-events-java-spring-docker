package com.zwemmen.psv.generic;

import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Abstract base service including the generic repository to be used by the specific services and the
 * transactional annotation.
 *
 * @author afernandez
 */
@Transactional
public abstract class BaseService<T, ID extends Serializable, R extends BaseRepository<T, ID>>
        implements Service<T, ID> {

    protected R repository;

    public BaseService(R repository) {
        this.repository = repository;
    }
}