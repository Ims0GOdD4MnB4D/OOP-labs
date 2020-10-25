package collections;

import model.ProductPackage;

public class ProductPackagePricePair {
    private ProductPackage key;
    private float value;


    public ProductPackagePricePair(ProductPackage key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public ProductPackage getKey() {
        return key;
    }

    public float getValue() {
        return value;
    }

    public void setKey(ProductPackage key) {
        this.key = key;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}