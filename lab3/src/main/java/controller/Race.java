package controller;

import exceptions.InvalidDistance;
import exceptions.NoVehiclesFound;
import model.AirVehicle;
import model.LandVehicle;
import model.VehicleType;

import java.util.*;

public class Race {
    private final float distance;
    private final List <VehicleType> raceMembers = new ArrayList<>();

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

    public Race(List<LandVehicle> landVehicleList, List<AirVehicle> airVehicleList, float distance) {
        this.raceMembers.addAll(landVehicleList);
        this.raceMembers.addAll(airVehicleList);
        this.distance = distance;
    }

    public VehicleType startRace() throws InvalidDistance {
        if(distance <= 0)
            throw new InvalidDistance();
        if(!raceMembers.isEmpty())
            return commonRace();
        throw new NoVehiclesFound();
    }

    private VehicleType commonRace() {
        return Collections.min(raceMembers, Comparator.comparing
                (VehicleType -> VehicleType.timeToPassTheDistance(distance)));
    }

    @Override
    public String toString() {
        return "Race{" +
                "distance=" + distance +
                ", raceMembers=" + raceMembers +
                '}';
    }
}
