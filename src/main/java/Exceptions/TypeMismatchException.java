package Exceptions;

import ini.ValueType;

import java.lang.reflect.Type;

public class TypeMismatchException extends Exception {

    public TypeMismatchException(ValueType expected, ValueType actual) {
        super(expected.name() + " value type expected, but was " + actual.name());
    }
}