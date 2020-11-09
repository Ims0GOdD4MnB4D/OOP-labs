package model.landVehicles;

import model.LandVehicle;

public class Centaur extends LandVehicle {
    private Integer landVehSpeed;
    private Integer timeTillRest;

    public Centaur(Integer landVehSpeed, Integer timeTillRest) {
        super(landVehSpeed, timeTillRest);
        this.landVehSpeed = landVehSpeed;
        this.timeTillRest = timeTillRest;
    }

    @Override
    protected float getRestDuration(int curPoint) {
        return 2;
    }

    @Override
    public Integer getLandVehSpeed() {
        return landVehSpeed;
    }

    @Override
    public void setLandVehSpeed(Integer landVehSpeed) {
        this.landVehSpeed = landVehSpeed;
    }

    @Override
    public Integer getTimeTillRest() {
        return timeTillRest;
    }

    @Override
    public void setTimeTillRest(Integer timeTillRest) {
        this.timeTillRest = timeTillRest;
    }

    @Override
    public String toString() {
        return "Centaur{" +
                "landVehSpeed=" + landVehSpeed +
                ", timeTillRest=" + timeTillRest +
                '}';
    }
}
