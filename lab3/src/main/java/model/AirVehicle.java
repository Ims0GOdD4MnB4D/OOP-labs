package model;

public abstract class AirVehicle extends VehicleType {
    private Integer speed;

    public AirVehicle(Integer speed) {
        super(speed);
        this.speed = speed;
    }

    protected abstract float cuttedDistance(float distance);

    @Override
    public float timeToPassTheDistance(float distance) {
        return (distance - cuttedDistance(distance))/speed;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "AirVehicle{" +
                "speed=" + speed +
                '}';
    }
}
