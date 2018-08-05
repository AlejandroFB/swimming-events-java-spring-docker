package com.zwemmen.psv.api.generic.mapper;

/**
 *
 * Mapping definition.
 *
 * @param <Source> The source
 * @param <Dest> The destination
 *
 * @author afernandez
 */
public interface MappingDefinition<Source, Dest> {

    /**
     * Map source object to destination object.
     *
     * @param source <code>Source</code>
     * @return <code>Dest</code>
     */
    Dest map(Source source);

    /**
     * Map source object to destination object. Get destination object by reference, for situations where getting
     * the destination entity from database for an update.
     *
     * @param source <code>Source</code>
     * @param dest   <code>Dest</code>
     * @return the <code>Dest</code>
     */
    Dest map(Source source, Dest dest);
}