import controller.ShopManager;
import exceptions.InvalidPrice;
import exceptions.InvalidProductQuantity;
import exceptions.NoSuchProductFound;
import exceptions.NoSuchProductPackageFound;
import exceptions.NoSuchShopFound;
import exceptions.NotEnoughProductQuantity;
import model.Product;
import model.ProductPackage;
import model.Shop;

import java.util.ArrayList;
import java.util.List;

public class Lab2App {
    public static void main(String[] args)
            throws InvalidProductQuantity, InvalidPrice, NoSuchProductFound, NoSuchShopFound, NoSuchProductPackageFound, NotEnoughProductQuantity {
        Shop centralDepartmentStore = new Shop("ЦУМ", "Петровка ул., 2");
        Shop houseOfLeningradTrade = new Shop("ДЛТ", "Конюшенная ул., 21–23");
        Product yeezyBoost = new Product("yeezy");
        Product airJordanLow = new Product("jordan");
        Product dunkTravis = new Product("travis");
        centralDepartmentStore.addProduct(yeezyBoost, 299, 20);
        centralDepartmentStore.changePrice(yeezyBoost, 350);
        centralDepartmentStore.addProduct(airJordanLow, 499, 15);
        houseOfLeningradTrade.addProduct(yeezyBoost, 299, 20);
        houseOfLeningradTrade.addProduct(dunkTravis, 800, 15);
        houseOfLeningradTrade.addProduct(airJordanLow, 750, 20);
        ShopManager manager = new ShopManager(centralDepartmentStore, houseOfLeningradTrade);
        System.out.println(manager.getTheCheapestPrice(yeezyBoost));
        ProductPackage jordanPackage = new ProductPackage(airJordanLow, 5);
        ProductPackage yeezyPackage = new ProductPackage(yeezyBoost, 2);
        System.out.println(manager.getShopWithLeastCost(jordanPackage, yeezyPackage));
        List<ProductPackage> whatYouCanBuy;
        whatYouCanBuy = houseOfLeningradTrade.getProductListByPrice(1000);
        System.out.println(whatYouCanBuy);
        System.out.println(houseOfLeningradTrade.getProductPackage(yeezyBoost).getProductPackage().getQuantity());
        ProductPackage yeezyPackageToBuy = new ProductPackage(yeezyBoost, 19);
        houseOfLeningradTrade.buyProductPackage(yeezyPackageToBuy);
        whatYouCanBuy = houseOfLeningradTrade.getProductListByPrice(1000);
        System.out.println(whatYouCanBuy);
        System.out.println(houseOfLeningradTrade.getProductPackage(yeezyBoost).getProductPackage().getQuantity());
    }
}
