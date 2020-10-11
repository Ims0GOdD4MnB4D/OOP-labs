package ini.property;


public class Property {
    private String key;
    private String val;

    public Property(String key, String val) {
        this.key = key;
        this.val = val;
    }

    public Property() {
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
}
