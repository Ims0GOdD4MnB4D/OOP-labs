package model.airVehicles;

import model.AirVehicle;

public class FlyingCarpet extends AirVehicle {
    private Integer speed;

    public FlyingCarpet(Integer speed) {
        super(speed);
        this.speed = speed;
    }

    @Override
    protected float cuttedDistance(float distance) {
        if(distance < 1000)
            return 0;
        else if(distance < 5000)
            return (float) (distance * 0.03);
        else if(distance < 10000)
            return (float) (distance * 0.1);
        else
            return (float) (distance * 0.05);
    }

    @Override
    public Integer getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "FlyingCarpet{" +
                "speed=" + speed +
                '}';
    }
}
