package Exceptions;

import ini.ValueType;

import java.lang.reflect.Type;

public class NonValidTypeException extends Exception {

    public NonValidTypeException(ValueType expected) {
        super("value type is not " + expected.name());
    }
}