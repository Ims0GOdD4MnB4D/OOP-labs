import controller.ShopManager;
import exceptions.InvalidPrice;
import exceptions.InvalidProductQuantity;
import exceptions.NoSuchProductFound;
import exceptions.NoSuchProductPackageFound;
import exceptions.NoSuchShopFound;
import exceptions.NotEnoughProductQuantity;
import model.PricedProductPackage;
import model.Product;
import model.ProductPackage;
import model.Shop;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ShopTest {
    @Test
    public void addingProductTest() throws InvalidPrice, NoSuchProductFound, InvalidProductQuantity, NoSuchShopFound {
        Product yeezyBoost = new Product("yeezy");
        Shop houseOfLeningradTrade = new Shop("ДЛТ", "Конюшенная ул., 21–23");
        houseOfLeningradTrade.addProduct(yeezyBoost, 350, 20);
        houseOfLeningradTrade.changePrice(yeezyBoost, 300);
        String expected = new PricedProductPackage(yeezyBoost, 20, 300).toString();
        String actual = houseOfLeningradTrade.getPricedProductPackage(yeezyBoost).toString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getTheCheapestPriceTesting() throws InvalidProductQuantity, InvalidPrice {
        Product yeezyBoost = new Product("yeezy");
        Product airJordanLow = new Product("jordan");
        Product dunkTravis = new Product("travis");
        Shop houseOfLeningradTrade = new Shop("ДЛТ", "Конюшенная ул., 21–23");
        Shop centralDepartmentStore = new Shop("ЦУМ", "Петровка ул., 2");
        centralDepartmentStore.addProduct(yeezyBoost, 299, 20);
        centralDepartmentStore.addProduct(airJordanLow, 499, 15);
        houseOfLeningradTrade.addProduct(yeezyBoost, 299, 20);
        houseOfLeningradTrade.addProduct(dunkTravis, 800, 15);
        houseOfLeningradTrade.addProduct(airJordanLow, 750, 20);
        ShopManager manager = new ShopManager(centralDepartmentStore, houseOfLeningradTrade);
        String expected = "ДЛТ";
        String actual = manager.getTheCheapestPrice(yeezyBoost).getName();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void buyProductPackageTesting() throws InvalidProductQuantity, InvalidPrice, NoSuchProductPackageFound, NotEnoughProductQuantity, NoSuchShopFound {
        Product yeezyBoost = new Product("yeezy");
        Shop houseOfLeningradTrade = new Shop("ДЛТ", "Конюшенная ул., 21–23");
        houseOfLeningradTrade.addProduct(yeezyBoost, 299, 20);
        ProductPackage yeezyToBuy = new ProductPackage(yeezyBoost, 15);
        houseOfLeningradTrade.buyProductPackage(yeezyToBuy);
        int expected = 5;
        int actual = houseOfLeningradTrade.getPricedProductPackage(yeezyBoost).getProductPackage().getQuantity();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getShopWithLeastCostTesting() throws InvalidProductQuantity, InvalidPrice, NoSuchShopFound, NoSuchProductPackageFound {
        Shop centralDepartmentStore = new Shop("ЦУМ", "Петровка ул., 2");
        Shop houseOfLeningradTrade = new Shop("ДЛТ", "Конюшенная ул., 21–23");
        Product yeezyBoost = new Product("yeezy");
        Product airJordanLow = new Product("jordan");
        Product dunkTravis = new Product("travis");
        centralDepartmentStore.addProduct(yeezyBoost, 299, 20);
        centralDepartmentStore.addProduct(airJordanLow, 499, 15);
        centralDepartmentStore.addProduct(dunkTravis, 900, 10);
        houseOfLeningradTrade.addProduct(dunkTravis, 800, 15);
        houseOfLeningradTrade.addProduct(yeezyBoost, 299, 20);
        houseOfLeningradTrade.addProduct(airJordanLow, 750, 20);
        ShopManager manager = new ShopManager(centralDepartmentStore, houseOfLeningradTrade);
        ProductPackage jordanPackage = new ProductPackage(airJordanLow, 5);
        ProductPackage yeezyPackage = new ProductPackage(yeezyBoost, 2);
        ProductPackage travisPackage = new ProductPackage(dunkTravis, 1);
        String expected = "ЦУМ";
        String actual = manager.getShopWithLeastCost(jordanPackage, yeezyPackage, travisPackage).getName();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getProductListByPriceTesting() throws InvalidProductQuantity, InvalidPrice {
        Shop houseOfLeningradTrade = new Shop("ДЛТ", "Конюшенная ул., 21–23");
        Product yeezyBoost = new Product("yeezy");
        Product airJordanLow = new Product("jordan");
        Product dunkTravis = new Product("travis");
        houseOfLeningradTrade.addProduct(yeezyBoost, 299, 20);
        houseOfLeningradTrade.addProduct(airJordanLow, 750, 20);
        houseOfLeningradTrade.addProduct(dunkTravis, 800, 15);
        List<ProductPackage> expected = new ArrayList<>();
        ProductPackage yeezyExpected = new ProductPackage(yeezyBoost, 3);
        ProductPackage jordanExpected = new ProductPackage(airJordanLow, 1);
        ProductPackage travisExpected = new ProductPackage(dunkTravis, 1);
        List<ProductPackage> actual = houseOfLeningradTrade.getProductListByPrice(1000);
        expected.add(yeezyExpected);
        expected.add(jordanExpected);
        expected.add(travisExpected);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = InvalidPrice.class)
    public void InvalidPriceTesting() throws InvalidProductQuantity, InvalidPrice {
        Shop houseOfLeningradTrade = new Shop("ДЛТ", "Конюшенная ул., 21–23");
        Product yeezyBoost = new Product("yeezy");
        houseOfLeningradTrade.addProduct(yeezyBoost, -10, 20);
    }

    @Test(expected = InvalidProductQuantity.class)
    public void InvalidProductQuantityTesting() throws InvalidProductQuantity, InvalidPrice {
        Shop houseOfLeningradTrade = new Shop("ДЛТ", "Конюшенная ул., 21–23");
        Product yeezyBoost = new Product("yeezy");
        houseOfLeningradTrade.addProduct(yeezyBoost, 300, -5);
    }

    @Test(expected = NoSuchProductFound.class)
    public void NoSuchProductFoundTestin() throws InvalidPrice, NoSuchProductFound {
        Shop houseOfLeningradTrade = new Shop("ДЛТ", "Конюшенная ул., 21–23");
        Product dunkTravis = new Product("travis");
        houseOfLeningradTrade.changePrice(dunkTravis, 800);
    }

    @Test(expected = NoSuchProductPackageFound.class)
    public void NoSuchProductPackageFoundException() throws NoSuchProductPackageFound, NotEnoughProductQuantity {
        Shop houseOfLeningradTrade = new Shop("ДЛТ", "Конюшенная ул., 21–23");
        Product dunkTravis = new Product("travis");
        ProductPackage travisPack = new ProductPackage(dunkTravis, 20);
        houseOfLeningradTrade.buyProductPackage(travisPack);
    }

}
