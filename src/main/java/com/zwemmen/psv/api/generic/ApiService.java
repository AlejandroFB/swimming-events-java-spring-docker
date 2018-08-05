package com.zwemmen.psv.api.generic;

import java.util.List;

/**
 * API service interface, with the base methods the different API services provide.
 *
 * @author afernandez
 */
public interface ApiService<INPUT extends ApiInputView, OUTPUT extends ApiOutputView, ID> {

    List<OUTPUT> findAll();

    OUTPUT findOne(ID entityId);

    OUTPUT create(INPUT viewObject);

    OUTPUT update(ID entityId, INPUT viewObject);
}