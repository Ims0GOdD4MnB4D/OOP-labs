package controller;

import exceptions.InvalidDistance;
import exceptions.NoVehiclesFound;
import model.AirVehicle;
import model.LandVehicle;
import model.VehicleType;

import java.util.*;

public class Race {
    private final float distance;
    private final List <LandVehicle> landVehicles;
    private final List <AirVehicle> airVehicles;

    public static class Builder {
        private float distance;
        private List <LandVehicle> landRace = new ArrayList<>();
        private List <AirVehicle> airRace = new ArrayList<>();

        public Builder landRace(LandVehicle... landVehicles) {
            landRace = Arrays.asList(landVehicles);
            return this;
        }

        public Builder airRace(AirVehicle ... airVehicles) {
            airRace = Arrays.asList(airVehicles);
            return this;
        }

        public Builder distance(float distance) {
            this.distance = distance;
            return this;
        }

        public Race build() {
            return new Race(landRace, airRace, distance);
        }
    }

    public Race(List <LandVehicle> landVehicleList, List <AirVehicle> airVehicleList, float distance) {
        landVehicles = landVehicleList;
        airVehicles = airVehicleList;
        this.distance = distance;
    }

    public VehicleType startRace() throws InvalidDistance {
        if(distance <= 0)
            throw new InvalidDistance();
        if(!landVehicles.isEmpty() && !airVehicles.isEmpty())
            return commonRace();
        if(!landVehicles.isEmpty())
            return landVehicleRace();
        if(!airVehicles.isEmpty())
            return airVehicleRace();
        throw new NoVehiclesFound();
    }

    private VehicleType commonRace() {
        VehicleType landWinner = Collections.min(landVehicles, Comparator.comparing
                (LandVehicle -> LandVehicle.timeToPassTheDistance(distance)));
        VehicleType airWinner = Collections.min(airVehicles, Comparator.comparing
                (AirVehicle -> AirVehicle.timeToPassTheDistance(distance)));
        return landWinner.timeToPassTheDistance(distance) < airWinner.timeToPassTheDistance(distance)
                ? landWinner : airWinner;
    }

    private LandVehicle landVehicleRace() {
        return Collections.min(landVehicles, Comparator.comparing
                (LandVehicle -> LandVehicle.timeToPassTheDistance(distance)));
    }

    private AirVehicle airVehicleRace() {
        return Collections.min(airVehicles, Comparator.comparing
                (AirVehicle -> AirVehicle.timeToPassTheDistance(distance)));
    }

    @Override
    public String toString() {
        return "Race{" +
                "landRace=" + landVehicles +
                ", airRace=" + airVehicles +
                '}';
    }
}
