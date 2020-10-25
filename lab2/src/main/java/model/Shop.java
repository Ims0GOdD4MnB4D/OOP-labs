package model;

import exceptions.*;

import java.util.*;

public class Shop {

    private List<PricedProductPackage> productList = new ArrayList<>();
    private final int shopID;
    private static int nextID = 0;

    public Shop() {
        shopID = nextID;
        ++nextID;
    }

    public Shop(ArrayList<PricedProductPackage> productList) {
        this.productList = productList;
        shopID = nextID;
        ++nextID;
    }

    public List<PricedProductPackage> getProductList() {
        return this.productList;
    }

    public void addProduct(ProductPackage productPackage, Integer productPrice) throws InvalidPrice {
        if(productPrice < 0)
            throw new InvalidPrice();
        productList.add(new PricedProductPackage(productPackage, productPrice));
    }

    public void addProduct(Product product, int price, Integer productQuantity) throws InvalidProductQuantity, InvalidPrice {
        if(price < 0)
            throw new InvalidPrice();
        if(productQuantity < 0)
            throw new InvalidProductQuantity();
        productList.add(new PricedProductPackage(new ProductPackage(product, productQuantity), price));
    }

    public void changePrice(Product product, Integer newPrice) throws NoSuchProductFound, InvalidPrice {
        if(newPrice < 0)
            throw new InvalidPrice();
        for(PricedProductPackage item : productList) {
            if(item.getProductPackage().getProduct().getProductID() == product.getProductID()) {
                item.setProductPrice(newPrice);
                return;
            }
        }
        throw new NoSuchProductFound();
    }

    public void addPackage(PricedProductPackage newPack) throws InvalidPrice, InvalidProductQuantity {
        if(newPack.getProductPrice() < 0)
            throw new InvalidPrice();
        if(newPack.getProductPackage().getQuantity() < 0)
            throw new InvalidProductQuantity();
        for(PricedProductPackage item : productList) {
            if(item.getProductPackage().getProduct().getProductID()
                    == newPack.getProductPackage().getProduct().getProductID()) {
                item.getProductPackage().setProductQuantity(
                        item.getProductPackage().getQuantity() + newPack.getProductPackage().getQuantity());
                return;
            }
        }
        productList.add(newPack);
    }

    public List<ProductPackage> getProductListByPrice(Integer money) {
        List<ProductPackage> whatYouCanBuy= new ArrayList<>();
        for(PricedProductPackage item : productList) {
            if(item.getProductPrice() < money) {
                whatYouCanBuy.add(new ProductPackage(item.getProductPackage().getProduct(),
                        (int) (money/item.getProductPrice())));
            }
        }
        return whatYouCanBuy;
    }

    public void buyProductPackage(PricedProductPackage ... pricedProductPackages)
            throws NoSuchProductPackageFound, NotEnoughProductQuantity {
        List<PricedProductPackage> productPackageList = Arrays.asList(pricedProductPackages);
        for(PricedProductPackage curPack : productPackageList) {
            if(!isContainEnoughProducts(curPack)) {
                throw new NotEnoughProductQuantity();
            }
            getProductPackage(curPack).getProductPackage().setProductQuantity
                    (getProductPackage(curPack).getProductPackage().getQuantity()
                            - curPack.getProductPackage().getQuantity());
        }
    }

    public PricedProductPackage getProductPackageIfExists(ProductPackage productPackage) {
        for(PricedProductPackage item : productList) {
            if(item.getProductPackage().getProduct().getProductID()
                    == productPackage.getProduct().getProductID())
                return item;
        }
        return null;
    }

    public PricedProductPackage getProductPackage(Product product) {
        for(PricedProductPackage item : productList) {
            if(item.getProductPackage().getProduct().getProductID() == product.getProductID())
                return item;
        }
        return null;
    }

    public PricedProductPackage getProductPackage(ProductPackage productPackage) {
        for(PricedProductPackage item : productList) {
            if(item.getProductPackage().getProduct().getProductID()
                    == productPackage.getProduct().getProductID())
                return item;
        }
        return null;
    }

    public PricedProductPackage getProductPackage(PricedProductPackage productPackage) {
        for(PricedProductPackage item : productList) {
            if(item.getProductPackage().getProduct().getProductID()
                    == productPackage.getProductPackage().getProduct().getProductID())
                return item;
        }
        return null;
    }

    public Product getProduct(ProductPackage productPackage) {
        for(PricedProductPackage item : productList) {
            if(item.getProductPackage().getProduct().getProductID() == productPackage.getProduct().getProductID())
                return item.getProductPackage().getProduct();
        }
        return null;
    }

    public boolean containsProductPackageCollection(List<PricedProductPackage> productPackageList) {
        for(PricedProductPackage item : productPackageList) {
            if(!isContainPackage(item))
                return false;
        }
        return true;
    }

    public boolean isContainEnoughProducts(PricedProductPackage pricedProductPackage) throws NoSuchProductPackageFound{
        if(!isContainPackage(pricedProductPackage)) {
            throw new NoSuchProductPackageFound();
        }
        return getProductPackage(pricedProductPackage).getProductPackage().getQuantity()
                >= pricedProductPackage.getProductPackage().getQuantity();
    }

    private boolean isContainPackage(PricedProductPackage pricedProductPackage) {
        for(PricedProductPackage item : productList) {
            if(item.getProductPackage().getProduct().getProductID()
                    == pricedProductPackage.getProductPackage().getProduct().getProductID()
                && item.getProductPrice() == pricedProductPackage.getProductPrice())
                return true;
        }
        return false;
    }

    public boolean isContainProduct(Product product) {
        for(PricedProductPackage item : productList) {
            if(item.getProductPackage().getProduct().getProductID() == product.getProductID())
                return true;
        }
        return false;
    }

    public Product getProductByID(Product product) {
        if(isContainProduct(product))
            return product;
        return null;
    }


    @Override
    public String toString() {
        return "Shop{" +
                "productList=" + productList +
                '}';
    }
}
