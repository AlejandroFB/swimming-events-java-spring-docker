package com.zwemmen.psv.generic;

import java.io.Serializable;
import java.util.List;

/**
 * Defines the contract for all application services.
 *
 * @author afernandez
 */
public interface Service<T, ID extends Serializable> {

    T findOne(ID id);

    List<T> findAll();

    T save(T t);

    void delete(T t);
}