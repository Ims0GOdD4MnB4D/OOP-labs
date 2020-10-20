package Exceptions;

import ini.ValueType;

public class NonValidTypeException extends Exception {
    public NonValidTypeException() {
    }

    public NonValidTypeException(String message) {
        super(message);
    }

    public NonValidTypeException(ValueType expected) {
        super("value type is not " + expected.name());
    }
}