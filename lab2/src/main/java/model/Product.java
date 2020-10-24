package model;

import java.util.Objects;

public class Product {
    private String productName;
    private int productPrice;
    private final int productID;
    private static int nextID = 0;

    public Product(String name, int price) {
        this.productName = name;
        this.productPrice = price;
        productID = nextID;
        ++nextID;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public int getProductID() {
        return productID;
    }


    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, productPrice, productID);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
