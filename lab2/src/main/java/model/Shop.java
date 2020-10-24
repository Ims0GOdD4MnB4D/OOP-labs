package model;

import java.util.*;

public class Shop {

    private List<ProductPackage> productList = new ArrayList<>();
    private final int shopID;
    private static int nextID = 0;

    public Shop() {
        shopID = nextID;
        ++nextID;
    }

    public Shop(ArrayList<ProductPackage> productList) {
        this.productList = productList;
        shopID = hashCode();
    }

    public List<ProductPackage> getProductList() {
        return this.productList;
    }

    public void addProduct(Product product, Integer productQuantity) {
        productList.add(new ProductPackage(product, productQuantity));
    }

    public void addProduct(String productName, int price, Integer productQuantity) {

    }

    public void changePrice(Product product, Integer newPrice) {
        for(ProductPackage item : productList) {
            if(item.getProduct().getProductID() == product.getProductID()) {
                item.getProduct().setProductPrice(newPrice);
            }
        }
        // no such product found exception
        throw new RuntimeException();
    }

    public void addPackage(ProductPackage newPack) {
//        if(productList.contains(newPack)) {
//            productList.
//        }
        for(ProductPackage item : productList) {
            if(item.getProduct().getProductID() == newPack.getProduct().getProductID()) {
                item.setProductQuantity(
                        item.getQuantity() + newPack.getQuantity());
                return;
            }
        }
        productList.add(newPack);
    }

    public ProductPackage containPackage(ProductPackage productPackage) {
        for(ProductPackage item : productList) {
            if(item.getProduct().getProductID() == productPackage.getProduct().getProductID())
                return item;
        }
        throw new RuntimeException();
    }

    public boolean containsCollection(List<ProductPackage> productPackageList) {
        for(ProductPackage item : productList) {
            if(!isContainPackage(item))
                return false;
        }
        return true;
    }

    public boolean isContainPackage(ProductPackage productPackage) {
        for(ProductPackage item : productList) {
            if(item.getProduct().getProductID() == productPackage.getProduct().getProductID()
                && item.getQuantity() <= productPackage.getQuantity())
                return true;
        }
        return false;
    }

    public boolean isContainProduct(Product product) {
        for(ProductPackage item : productList) {
            if(item.getProduct().getProductID() == product.getProductID())
                return true;
        }
        return false;
    }

    public Product getProductByID(Product product) {
        if(isContainProduct(product))
            return product;
        throw new RuntimeException();
    }

    @Override
    public int hashCode() {
        return Objects.hash(productList, shopID);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Shop{" +
                "productList=" + productList +
                '}';
    }
}
