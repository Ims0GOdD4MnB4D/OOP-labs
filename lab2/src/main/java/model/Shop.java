package model;

import exceptions.InvalidPrice;
import exceptions.InvalidProductQuantity;
import exceptions.NoSuchProductFound;
import exceptions.NoSuchProductPackageFound;
import exceptions.NoSuchShopFound;
import exceptions.NotEnoughProductQuantity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Shop {

    private List<PricedProductPackage> productList = new ArrayList<>();
    private final int shopID;
    private static int nextID = 0;
    private String name;
    private String address;


    public Shop(String name, String address) {
        this.name = name;
        this.address = address;
        shopID = nextID;
        ++nextID;
    }

    public String getName() {
        return name;
    }

    public Shop(ArrayList<PricedProductPackage> productList) {
        this.productList = productList;
        shopID = nextID;
        ++nextID;
    }

    public List<PricedProductPackage> getProductList() {
        return this.productList;
    }

    public void addProduct(Product product, int price, Integer productQuantity) throws InvalidProductQuantity, InvalidPrice {
        if (price < 0)
            throw new InvalidPrice();
        if (productQuantity < 0)
            throw new InvalidProductQuantity();
        productList.add(new PricedProductPackage(new ProductPackage(product, productQuantity), price));
    }

    public void changePrice(Product product, Integer newPrice) throws NoSuchProductFound, InvalidPrice {
        if (newPrice < 0)
            throw new InvalidPrice();
        for (PricedProductPackage item : productList) {
            if (item.getProductPackage().getProduct().getProductID() == product.getProductID()) {
                item.setProductPrice(newPrice);
                return;
            }
        }
        throw new NoSuchProductFound();
    }

    public List<ProductPackage> getProductListByPrice(Integer money) {
        List<ProductPackage> whatYouCanBuy = new ArrayList<>();
        for (PricedProductPackage item : productList) {
            if (item.getProductPrice() < money && item.getProductPackage().getQuantity() != 0) {
                whatYouCanBuy.add(new ProductPackage(item.getProductPackage().getProduct(),
                        Math.min((int) (money / item.getProductPrice()),
                                item.getProductPackage().getQuantity())));
            }
        }
        return whatYouCanBuy;
    }

    public void buyProductPackage(ProductPackage... productPackages)
            throws NoSuchProductPackageFound, NotEnoughProductQuantity {
        List<ProductPackage> productPackageList = Arrays.asList(productPackages);
        for (ProductPackage curPack : productPackageList) {
            if (!isContainEnoughProducts(curPack)) {
                throw new NotEnoughProductQuantity();
            }
            getProductPackage(curPack.getProduct()).getProductPackage().setProductQuantity
                    (getProductPackage(curPack.getProduct()).getProductPackage().getQuantity()
                            - curPack.getQuantity());
        }
    }

    public int tryBuyPackage(List<ProductPackage> productPackageList) throws NoSuchProductPackageFound {
        int curCost = 0;
        if (containsProductPackageCollection(productPackageList))
            for (ProductPackage productPackage : productPackageList)
                if (isContainProduct(productPackage.getProduct())) {
                    curCost += getProductPackage(productPackage.getProduct()).getProductPrice()
                            * productPackage.getQuantity();
                }
        if (curCost != 0)
            return curCost;
        else
            throw new NoSuchProductPackageFound();
    }

    public PricedProductPackage getProductPackage(Product product) {
        for (PricedProductPackage item : productList) {
            if (item.getProductPackage().getProduct().getProductID() == product.getProductID())
                return item;
        }
        return null;
    }

    public PricedProductPackage getPricedProductPackage(Product product) throws NoSuchShopFound {
        for (PricedProductPackage item : productList) {
            if (item.getProductPackage().getProduct().getProductID() == product.getProductID())
                return item;
        }
        throw new NoSuchShopFound();
    }

    public boolean containsProductPackageCollection(List<ProductPackage> productPackageList) {
        for (ProductPackage item : productPackageList) {
            if (!isContainProduct(item.getProduct()))
                return false;
        }
        return true;
    }

    public boolean isContainEnoughProducts(ProductPackage productPackage) throws NoSuchProductPackageFound {
        if (!isContainProduct(productPackage.getProduct())) {
            throw new NoSuchProductPackageFound();
        }
        return getProductPackage(productPackage.getProduct()).getProductPackage().getQuantity()
                >= productPackage.getQuantity();
    }

    public boolean isContainProduct(Product product) {
        for (PricedProductPackage item : productList) {
            if (item.getProductPackage().getProduct().getProductID() == product.getProductID())
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return name;
    }
}
