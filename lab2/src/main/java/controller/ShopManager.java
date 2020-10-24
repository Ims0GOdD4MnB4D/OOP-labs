package controller;

import module.Product;
import module.ProductPackage;
import module.Shop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShopManager {
    public Shop getTheCheapestPrice(Product product, Shop... sh) {
        int resPrice = Integer.MAX_VALUE;
        Shop ans = null;
        for(Shop cur : sh) {
            if(cur.isContainProduct(product) && 
                    cur.getProductByID(product).getProductPrice() <= resPrice) {
                ans = cur;
                resPrice = cur.getProductByID(product).getProductPrice();
            }
        }
        if(resPrice == Integer.MAX_VALUE)
            throw new RuntimeException();
        return ans;
    }

    public List<ProductPackage> getProductListByPrice(Shop sh, Integer money) {
        List<ProductPackage> whatCanYouBuy= new ArrayList<>();
        for(ProductPackage item : sh.getProductList()) {
            if(item.getProduct().getProductPrice() < money) {
                whatCanYouBuy.add(new ProductPackage(item.getProduct(),
                        money/item.getProduct().getProductPrice()));
            }
        }
        if(whatCanYouBuy.isEmpty())
            whatCanYouBuy.add(new ProductPackage(new Product
                    ("GTFO my propety, you fooking beggar", 0), 0));
        return whatCanYouBuy;
    }

    public void buyProductPackage(Shop shop, ProductPackage ... productPackage) {
        List<ProductPackage> productPackageList = Arrays.asList(productPackage);
        boolean canBuy = false;
        for(ProductPackage curPack : productPackageList)
            if(shop.getProductList().contains(curPack) &&
                    shop.containPackage(curPack).getQuantity() >= curPack.getQuantity()) {
                canBuy = true;
            }
        if(canBuy) {
            for(ProductPackage curPack : productPackageList)
                if(shop.getProductList().contains(curPack) &&
                        shop.containPackage(curPack).getQuantity() >= curPack.getQuantity()) {
                    shop.containPackage(curPack).setProductQuantity(
                            shop.containPackage(curPack).getQuantity() - curPack.getQuantity());
                }
        }
        else
            throw new RuntimeException();
    }

    public Shop getShopWithLeastCost(List<ProductPackage> productPackages, Shop... shops) {
        List<Shop> shopList = Arrays.asList(shops);
        int resCost = Integer.MAX_VALUE;
        Shop resShop = null;
        for(Shop curShop : shopList) {
            int curCost = 0;
            if(curShop.containsCollection(productPackages))
                for(ProductPackage productPackage : curShop.getProductList())
                    if(productPackages.contains(productPackage)) {
                       curCost += productPackage.getProduct().getProductPrice() * productPackage.getQuantity();
                    }
            if(curCost <= resCost) {
                resCost = curCost;
                resShop = curShop;
            }
        }
        if(resShop != null)
            return resShop;
        throw new RuntimeException();
    }
}
