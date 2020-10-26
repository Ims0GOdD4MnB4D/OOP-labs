package model;

import collections.ProductQuantityPair;

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
    public String toString() {
        return "ProductPackage{" +
                "productPack=" + productPack +
                '}';
    }
}
