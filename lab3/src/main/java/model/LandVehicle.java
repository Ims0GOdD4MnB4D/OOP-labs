package model;

public abstract class LandVehicle extends VehicleType {
    private Integer landVehSpeed;
    private Integer timeTillRest;

    public LandVehicle(Integer landVehSpeed, Integer timeTillRest) {
        super(landVehSpeed);
        this.landVehSpeed = landVehSpeed;
        this.timeTillRest = timeTillRest;
    }

    protected abstract float getRestDuration(int curPoint);

    private float restDuration(float distance) {
        float restTime = 0;
        int point = 1;
        for (int i = 0; i < distance / (landVehSpeed * timeTillRest); ++i) {
            restTime += getRestDuration(point);
            ++point;
        }
        return restTime;
    }

    @Override
    public float timeToPassTheDistance(float distance) {
        return distance / landVehSpeed + restDuration(distance);
    }

    public Integer getTimeTillRest() {
        return timeTillRest;
    }

    public void setTimeTillRest(Integer timeTillRest) {
        this.timeTillRest = timeTillRest;
    }

    public Integer getLandVehSpeed() {
        return landVehSpeed;
    }

    public void setLandVehSpeed(Integer landVehSpeed) {
        this.landVehSpeed = landVehSpeed;
    }

    @Override
    public String toString() {
        return "LandVehicle{" +
                "landVehSpeed=" + landVehSpeed +
                ", timeTillRest=" + timeTillRest +
                '}';
    }
}
