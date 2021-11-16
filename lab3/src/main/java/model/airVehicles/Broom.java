package model.airVehicles;

import model.AirVehicle;

public class Broom extends AirVehicle {
    private Integer speed;

    public Broom(Integer speed) {
        super(speed);
        this.speed = speed;
    }

    @Override
    protected float cuttedDistance(float distance) {
        if (distance > 1000)
            return distance / 100000 * distance;
        else
            return 0;
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
        return "Broom{" +
                "speed=" + speed +
                '}';
    }
}
