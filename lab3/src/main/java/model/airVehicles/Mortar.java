package model.airVehicles;

import model.AirVehicle;

public class Mortar extends AirVehicle {
    private Integer speed;

    public Mortar(Integer speed) {
        super(speed);
        this.speed = speed;
    }

    @Override
    protected float cuttedDistance(float distance) {
        return distance * 0.06f;
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
        return "Mortar{" +
                "speed=" + speed +
                '}';
    }
}
