package module;

import collections.CustomPair;

public class ProductPackage {
    private CustomPair productPack;

    public ProductPackage(Product product, Integer productQuantity) {
        productPack.setKey(product);
        productPack.setValue(productQuantity);
    }

    public ProductPackage(Product product, Integer quantity, Integer newPrice) {
        productPack.setKey(product);
        productPack.getKey().setProductPrice(newPrice);
        productPack.setValue(quantity);
    }

    public ProductPackage(CustomPair productPack) {
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
}
