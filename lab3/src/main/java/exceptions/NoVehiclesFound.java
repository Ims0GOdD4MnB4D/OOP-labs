package exceptions;

public class NoVehiclesFound extends RuntimeException{
    public NoVehiclesFound() {
        super("No vehicles found for the race");
    }
}
