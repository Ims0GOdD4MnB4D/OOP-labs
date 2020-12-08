package ini.section;

import Exceptions.KeyNotFoundException;
import Exceptions.InvalidTypeException;
import ini.key.Key;

import java.util.*;


public class Section {
    private final String val;

    private Map<String, Key> data = new HashMap<>();

    public Section(String val) {
        this.val = val;
    }

    public Key getKey(String key) {
        try  {
            return data.get(key);
        } catch(RuntimeException ex) {
            throw new KeyNotFoundException();
        }
    }

    public String getVal() {
        return val;
    }

    public void addKey(Key key) {
        data.put(key.getKey(), key);
    }

    public int getInt(String key) {

        Key curKey;
        try  {
            curKey = getKey(key);
        } catch (RuntimeException ex) {
            throw new KeyNotFoundException();
        }
        try {
            return curKey.parseInt();
        } catch (RuntimeException ex) {
            throw new InvalidTypeException();
        }
    }

    public double getDouble(String key){
        Key curKey;
        try  {
            curKey = getKey(key);
        } catch (RuntimeException ex) {
            throw new KeyNotFoundException();
        }
        try {
            return curKey.tryDouble();
        } catch (RuntimeException ex) {
            throw new InvalidTypeException();
        }
    }
    public String getString(String key) throws KeyNotFoundException {
        try {
            return getKey(key).getVal();
        } catch (RuntimeException ex) {
            throw new KeyNotFoundException();
        }
    }
}
