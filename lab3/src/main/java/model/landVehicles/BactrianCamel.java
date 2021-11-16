package model.landVehicles;

import model.LandVehicle;

public class BactrianCamel extends LandVehicle {
    private Integer landVehSpeed;
    private Integer timeTillRest;

    public BactrianCamel(Integer landVehSpeed, Integer timeTillRest) {
        super(landVehSpeed, timeTillRest);
        this.landVehSpeed = landVehSpeed;
        this.timeTillRest = timeTillRest;
    }

    @Override
    protected float getRestDuration(int curPoint) {
        if (curPoint == 1)
            return 5;
        else
            return 10;
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
        return "BactrianCamel{" +
                "landVehSpeed=" + landVehSpeed +
                ", timeTillRest=" + timeTillRest +
                '}';
    }
}
