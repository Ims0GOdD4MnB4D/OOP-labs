package model;

import collections.ProductQuantityPair;

import java.util.Objects;

public class ProductPackage {
    private final ProductQuantityPair productPack;

    public ProductPackage(Product product, Integer productQuantity) {
        productPack = new ProductQuantityPair(product, productQuantity);
    }

    public ProductPackage(ProductQuantityPair productPack) {
        this.productPack = productPack;
    }

    public Product getProduct() {
        return productPack.getKey();
    }

    public Integer getQuantity() {
        return productPack.getValue();
    }

    public void setProductQuantity(Integer quantity) {
        productPack.setValue(quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPackage that = (ProductPackage) o;
        return Objects.equals(productPack, that.productPack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productPack);
    }

    @Override
    public String toString() {
        return "ProductPackage{" +
                "productPack=" + productPack +
                '}';
    }
}
