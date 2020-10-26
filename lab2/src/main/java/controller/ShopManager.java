package controller;

import exceptions.NoSuchProductPackageFound;
import exceptions.NoSuchShopFound;
import exceptions.NotEnoughProductQuantity;
import model.PricedProductPackage;
import model.Product;
import model.ProductPackage;
import model.Shop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShopManager {
    private final List<Shop> shopList;

    public ShopManager() {
        shopList = new ArrayList<>();
    }

    public ShopManager(Shop ... shops) {
        shopList = Arrays.asList(shops);
    }

    public void addShop(Shop shop) {
        shopList.add(shop);
    }

    public Shop getTheCheapestPrice(Product product) {
        float resPrice = Integer.MAX_VALUE;
        Shop resShop = null;
        for(Shop cur : shopList) {
            if(cur.isContainProduct(product) && 
                    cur.getProductPackage(product).getProductPrice() <= resPrice) {
                resShop = cur;
                resPrice = cur.getProductPackage(product).getProductPrice();
            }
        }
        if(resPrice == Integer.MAX_VALUE)
            throw new RuntimeException();
        return resShop;
    }

    public Shop getShopWithLeastCost(ProductPackage ... productPackages) throws NoSuchShopFound {
        List<ProductPackage> productPackageList = Arrays.asList(productPackages);
        int resCost = Integer.MAX_VALUE;
        Shop resShop = null;
        for(Shop curShop : shopList) {
            int curCost = 0;
            if(curShop.containsProductPackageCollection(productPackageList))
                for(ProductPackage productPackage : productPackageList)
                    if(curShop.containsProductPackage(productPackage)) {
                       curCost += curShop.getProductPackage(productPackage).getProductPrice()
                               * productPackage.getQuantity();
                    }
            if(curCost <= resCost) {
                resCost = curCost;
                resShop = curShop;
            }
        }
        if(resShop != null)
            return resShop;
        throw new NoSuchShopFound();
    }
}
