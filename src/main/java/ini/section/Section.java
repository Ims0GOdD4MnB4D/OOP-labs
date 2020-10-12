package ini.section;

import Exceptions.InvalidTypeException;
import Exceptions.PropertyNotFoundException;
import Exceptions.TypeMismatchException;
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

    public Optional<Property> getProperty(String key) {
        return Optional.ofNullable(
                data.get(key)
        );
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

    public void removeProperty(String key) {
        data.remove(key);
    }

    public int getInt(String key) throws TypeMismatchException, PropertyNotFoundException {
        Property property = getProperty(key)
                .orElseThrow(PropertyNotFoundException::new);

        if (property.getType().equals(ValueType.INT))
            return Integer.parseInt(property.getVal());
        else
            throw new TypeMismatchException(ValueType.INT, property.getType());
    }

    public double getDouble(String key) throws TypeMismatchException, PropertyNotFoundException {
        Property property = getProperty(key)
                .orElseThrow(PropertyNotFoundException::new);

        if (property.getType().equals(ValueType.DOUBLE))
            return Double.parseDouble(property.getVal());
        else if (property.getType().equals(ValueType.INT))
            return Integer.parseInt(property.getVal());
        else
            throw new TypeMismatchException(ValueType.DOUBLE, property.getType());
    }

    public String getString(String key) throws PropertyNotFoundException {
        return getProperty(key)
                .orElseThrow(PropertyNotFoundException::new)
                .getVal()
                .toString();
    }

    public Optional<Integer> tryGetInt(String key) {
        return getProperty(key).map(Integer.class::cast);
    }

    public Optional<Double> tryGetDouble(String key) {
        return getProperty(key).map(Double.class::cast);
    }

    public Optional<String> tryGetString(String key) {
        return getProperty(key).map(Object::toString);
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
