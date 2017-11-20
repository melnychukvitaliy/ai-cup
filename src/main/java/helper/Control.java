package helper;

import model.Location;
import model.Vehicle;

import java.util.List;

public class Control {

    public Location getCentralGroup(List<Vehicle> vehicles) {
        int totalX = 0, totalY = 0;
        for(Vehicle vehicle: vehicles) {
            totalX += vehicle.getX();
            totalY += vehicle.getY();
        }

        return new Location(totalX / vehicles.size(), totalY / vehicles.size());
    }
}
