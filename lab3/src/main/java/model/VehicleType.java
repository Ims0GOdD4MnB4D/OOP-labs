package model;

import java.util.Objects;

public abstract class VehicleType implements IVehicleType {
    private Integer vehSpeed;

    public VehicleType(Integer vehSpeed) {
        this.vehSpeed = vehSpeed;
    }

    @Override
    public abstract float timeToPassTheDistance(float distance);

    public Integer getVehSpeed() {
        return vehSpeed;
    }

    public void setVehSpeed(Integer vehSpeed) {
        this.vehSpeed = vehSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleType that = (VehicleType) o;
        return Objects.equals(vehSpeed, that.vehSpeed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehSpeed);
    }
}
