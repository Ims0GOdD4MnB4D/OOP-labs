package ini.property;


import Exceptions.InvalidTypeException;
import ini.ValueType;

import java.util.Objects;

public class Property {
    private String key;
    private String val;
    private ValueType valueType;


    public Property(String key, String val) throws InvalidTypeException {
        this.key = key;

        if (val instanceof String)
            valueType = ValueType.STRING;
        else if (Double.class.isAssignableFrom(val.getClass()))
            valueType = ValueType.DOUBLE;
        else if (Integer.class.isAssignableFrom(val.getClass()))
            valueType = ValueType.INT;
        else
            throw new InvalidTypeException();

        this.val = val;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getKey() {
        return key;
    }

    public String getVal() {
        return val;
    }


    public ValueType getType() {
        return valueType;
    }

    public Integer parseInt() {

        return Integer.parseInt(val);
    }

    public Double parseDouble() {

        return Double.parseDouble(val);
    }

    public ValueType tryParseInt() throws InvalidTypeException {

        if(parseInt() instanceof Integer) {
                return ValueType.INT;
            }

            throw new InvalidTypeException();
    }

    public ValueType tryParseDouble()  throws InvalidTypeException {

        if(parseDouble() instanceof Double) {
            return ValueType.DOUBLE;
        }

        throw new InvalidTypeException();
    }

    public static Property createProperty(String key, String val) {
        try {
                return new Property(key, val);
        } catch (InvalidTypeException ex) {
            throw new RuntimeException("Cannot create property with key " + key);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return key.equals(property.key) &&
                val.equals(property.val) &&
                valueType == property.valueType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, val, valueType);
    }

    @Override
    public String toString() {
        return "Property{" +
                "key='" + key + '\'' +
                ", val='" + val + '\'' +
                ", valueType=" + valueType +
                '}';
    }
}
