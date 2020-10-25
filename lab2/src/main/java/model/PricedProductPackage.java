package model;

import collections.ProductPackagePricePair;

public class PricedProductPackage {
    private final ProductPackagePricePair pricedProductPackage;

    public PricedProductPackage(ProductPackage productPackage, Integer productPrice) {
        pricedProductPackage = new ProductPackagePricePair(productPackage, productPrice);
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
}
