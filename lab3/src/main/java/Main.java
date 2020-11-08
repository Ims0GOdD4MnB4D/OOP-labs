import controller.Race;
import exceptions.InvalidDistance;
import model.airVehicles.Broom;
import model.airVehicles.Mortar;
import model.airVehicles.FlyingCarpet;
import model.landVehicles.BactrianCamel;
import model.landVehicles.Centaur;
import model.landVehicles.SpeedyCamel;
import model.landVehicles.YeezyBoost;

public class Main {
    public static void main(String[] args) throws InvalidDistance {
        Race race1 = new Race.Builder()
                .landRace(new BactrianCamel(10, 30),
                        new SpeedyCamel(40, 10),
                        new Centaur(15, 8),
                        new YeezyBoost(6, 60))
                .airRace(new FlyingCarpet(10),
                         new Mortar(8),
                         new Broom(20))
                .distance(550)
                .build();
        Race race2 = new Race.Builder()
                    .airRace(new FlyingCarpet(10),
                            new Mortar(8),
                            new Broom(10))
                    .distance(500)
                    .build();
        Race race3 = new Race.Builder().distance(10).build();
        System.out.println(race1.startRace());
        System.out.println(race2.startRace());
        System.out.println(race3.startRace());
    }
}
