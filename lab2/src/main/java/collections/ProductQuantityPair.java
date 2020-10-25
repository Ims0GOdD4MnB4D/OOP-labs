package collections;

import model.Product;

public class ProductQuantityPair {
        private Product key;
        private Integer value;


    public ProductQuantityPair(Product key, Integer value) {
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
