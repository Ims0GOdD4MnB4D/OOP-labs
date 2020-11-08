package model.landVehicles;

import model.LandVehicle;

public class SpeedyCamel extends LandVehicle {
    private Integer landVehSpeed;
    private Integer timeTillRest;

    public SpeedyCamel(Integer landVehSpeed, Integer timeTillRest) {
        super(landVehSpeed, timeTillRest);
        this.landVehSpeed = landVehSpeed;
        this.timeTillRest = timeTillRest;
    }

    @Override
    protected float getRestDuration(int curPoint) {
        if(curPoint == 1)
            return 5;
        else if(curPoint == 2)
            return 6.5f;
        else
            return 8;
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
        return "SpeedyCamel{" +
                "landVehSpeed=" + landVehSpeed +
                ", timeTillRest=" + timeTillRest +
                '}';
    }
}
