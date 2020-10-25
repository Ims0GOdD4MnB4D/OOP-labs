import controller.ShopManager;
import model.Shop;

public class Main {
    public static void main(String[] args) {
        Shop newShop = new Shop();
        ShopManager shopManage = new ShopManager(newShop);
    }
}
