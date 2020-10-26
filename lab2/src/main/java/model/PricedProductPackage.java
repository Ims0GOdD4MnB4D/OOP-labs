package model;

import collections.ProductPackagePricePair;

import java.util.Objects;

public class PricedProductPackage {
    private final ProductPackagePricePair pricedProductPackage;

    public PricedProductPackage(ProductPackage productPackage, Integer productPrice) {
        pricedProductPackage = new ProductPackagePricePair(productPackage, productPrice);
    }

    public PricedProductPackage(Product product, Integer productQuantity, Integer productPrice) {
        pricedProductPackage = new ProductPackagePricePair(new ProductPackage(product, productQuantity), productPrice);
    }

    public PricedProductPackage (ProductPackagePricePair pricedProductPackage) {
        this.pricedProductPackage = pricedProductPackage;
    }

    public ProductPackage getProductPackage() {
        return pricedProductPackage.getKey();
    }

    public float getProductPrice() {
        return pricedProductPackage.getValue();
    }

    public void setProductPrice(Integer newPrice) {
        pricedProductPackage.setValue(newPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pricedProductPackage);
    }

    @Override
    public String toString() {
        return "PricedProductPackage{" +
                "pricedProductPackage=" + pricedProductPackage +
                '}';
    }
}
