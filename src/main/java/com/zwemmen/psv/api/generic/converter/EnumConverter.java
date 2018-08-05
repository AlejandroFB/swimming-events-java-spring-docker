package com.zwemmen.psv.api.generic.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Converts a string to its enum representation. The converter has to be initialized with the class of the enum to
 * convert.
 *
 * @author afernandez
 */
public class EnumConverter<T, E> implements Converter<T, E> {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private Class<?> enumClass;

    public EnumConverter(Class<?> type) {
        this.enumClass = type;
    }

    @Override
    public T convert(E field) {
        if (field == null) {
            return null;
        }

        try {
            return (T) Enum.valueOf((Class<? extends Enum>) enumClass, field.toString().toUpperCase());
        } catch (Exception ex) {
            final String error = String.format("Can't convert to enum: %s", field);
            logger.error(error, ex.toString());

            throw new IllegalStateException(error, ex);
        }
    }
}