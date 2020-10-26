package collections;

import model.Product;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductQuantityPair that = (ProductQuantityPair) o;
        return Objects.equals(key, that.key) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        return "ProductQuantityPair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
