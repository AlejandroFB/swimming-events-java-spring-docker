package com.zwemmen.psv.api.generic.converter;

/**
 * Converts a given object to another object, possibly doing some processing in the middle.
 *
 * @author afernandez
 */
public interface Converter<T, E> {

    /**
     * Converts the object to another object.
     *
     * @param field The given object to convert
     * @return The result object already converted
     */
    T convert(E field);
}