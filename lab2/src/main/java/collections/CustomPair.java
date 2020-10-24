package collections;

import module.Product;

public class CustomPair {
        private Product key;
        private Integer value;


    public CustomPair(Product key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public Product getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }

    public void setKey(Product key) {
        this.key = key;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
