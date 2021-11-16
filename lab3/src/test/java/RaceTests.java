import controller.Race;
import exceptions.InvalidDistance;
import exceptions.NoVehiclesFound;
import model.VehicleType;
import model.airVehicles.Broom;
import model.airVehicles.FlyingCarpet;
import model.airVehicles.Mortar;
import model.landVehicles.BactrianCamel;
import model.landVehicles.Centaur;
import model.landVehicles.SpeedyCamel;
import model.landVehicles.YeezyBoost;
import org.junit.Assert;
import org.junit.Test;

public class RaceTests {
    @Test(expected = InvalidDistance.class)
    public void InvalidDistanceTesting() throws InvalidDistance {
        Race race = new Race.Builder()
                .airRace(new FlyingCarpet(10),
                        new Mortar(8),
                        new Broom(10))
                .distance(-10)
                .build();
        System.out.println(race.startRace());
    }

    @Test(expected = NoVehiclesFound.class)
    public void NoVehiclesFoundTesting() throws InvalidDistance {
        Race race = new Race.Builder()
                .distance(100)
                .build();

        System.out.println(race.startRace());
    }

    @Test
    public void landRaceTesting() throws InvalidDistance {
        Race race = new Race.Builder()
                .landRace(new BactrianCamel(10, 30),
                        new SpeedyCamel(40, 10),
                        new Centaur(15, 8),
                        new YeezyBoost(6, 60))
                .distance(550)
                .build();
        VehicleType expected = new SpeedyCamel(40, 10);
        VehicleType actual = race.startRace();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void airRaceTesting() throws InvalidDistance {
        Race race = new Race.Builder()
                .airRace(new FlyingCarpet(10),
                        new Mortar(8),
                        new Broom(20))
                .distance(1000)
                .build();
        VehicleType expected = new Broom(20);
        VehicleType actual = race.startRace();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void commonRaceTesting() throws InvalidDistance {
        Race race = new Race.Builder()
                .landRace(new BactrianCamel(10, 30),
                        new SpeedyCamel(40, 10),
                        new Centaur(15, 8),
                        new YeezyBoost(6, 60))
                .airRace(new FlyingCarpet(10),
                        new Mortar(8),
                        new Broom(20))
                .distance(550)
                .build();
        VehicleType expected = new SpeedyCamel(40, 10);
        VehicleType actual = race.startRace();
        Assert.assertEquals(expected, actual);
    }
}
