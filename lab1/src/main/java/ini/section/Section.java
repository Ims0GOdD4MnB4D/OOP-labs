package ini.section;

import Exceptions.InvalidTypeException;
import Exceptions.PropertyNotFoundException;
import Exceptions.NonValidTypeException;
import ini.ValueType;
import ini.property.Property;

import java.util.*;


public class Section {
    private final String val;

    private final Map<String, Property> data = new LinkedHashMap<>();

    public Section(String val) {
        this.val = val;
    }

    public Section(String val, Property ... properties) {
            this.val = val;

            List.of(properties).forEach(this::addProperty);
    }

    public Property getProperty(String key) throws PropertyNotFoundException {
        try  {
            return data.get(key);
        } catch(RuntimeException ex) {
            throw new PropertyNotFoundException();
        }
    }

    public String getVal() {
        return val;
    }

    public void addProperty(String key, String val) throws InvalidTypeException {
        data.put(key, new Property(key, val));
    }

    public void addProperty(Property property) {
        data.put(property.getKey(), property);
    }

    public int getInt(String key) throws NonValidTypeException, PropertyNotFoundException {

        Property property;
        try  {
            property = getProperty(key);
        } catch (RuntimeException ex) {
            throw new PropertyNotFoundException();
        }
        try {
            return property.parseInt();
        } catch (RuntimeException ex) {
            throw new NonValidTypeException(ValueType.INT);
        }
    }

    public double getDouble(String key) throws NonValidTypeException, PropertyNotFoundException {
        Property property;
        try  {
            property = getProperty(key);
        } catch (RuntimeException ex) {
            throw new PropertyNotFoundException();
        }
        try {
            return property.parseDouble();
        } catch (RuntimeException ex) {
            throw new NonValidTypeException(ValueType.DOUBLE);
        }
    }
    public String getString(String key) throws PropertyNotFoundException {
        try {
            return getProperty(key).getVal();
        } catch (RuntimeException ex) {
            throw new PropertyNotFoundException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Section section = (Section) o;
        return val.equals(section.val) &&
                data.equals(section.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, data);
    }

    @Override
    public String toString() {
        return "Section{" +
                "val='" + val + '\'' +
                ", data=" + data +
                '}';
    }
}
