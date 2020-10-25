package model;

import java.util.Objects;

public class Product {
    private String productName;
    private final int productID;
    private static int nextID = 0;

    public Product(String name, int price) {
        this.productName = name;
        productID = nextID;
        ++nextID;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductID() {
        return productID;
    }


    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, productID);
    }
}
