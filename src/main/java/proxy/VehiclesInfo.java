package proxy;

import model.Vehicle;
import model.VehicleType;
import model.VehicleUpdate;
import model.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VehiclesInfo {
    private Vehicle[] vehicles;

    private HashMap<Long, Integer> hm = new HashMap<>();
    private HashMap<Long, List<Integer>> vehiclesByPlayer = new HashMap<>();

    public VehiclesInfo(World world) {
        int i = 0;
        Vehicle[] newVehicles = world.getNewVehicles();
        while (i < newVehicles.length - 1) {
            hm.put(newVehicles[i].getId(), i);
            vehiclesByPlayer.putIfAbsent(newVehicles[i].getPlayerId(), new ArrayList<>());
            vehiclesByPlayer.get(newVehicles[i].getPlayerId()).add(i);

            i++;
        }
        this.vehicles = newVehicles;
    }

    public void updateVehicles(VehicleUpdate[] vehicleUpdate) {
        int i;
        for (i = 0; i < vehicleUpdate.length - 1; i++) {
            int id = hm.get(vehicleUpdate[i].getId());

            this.vehicles[id] = new Vehicle(this.vehicles[i], vehicleUpdate[i]);
        }
    }

    public Vehicle[] getVehicles() {
        return vehicles;
    }

    public HashMap<Long, List<Integer>> getVehiclesByPlayer() {
        return vehiclesByPlayer;
    }

    public List<Vehicle> getByType(List<Integer> vehiclesMap,VehicleType type) {
        List<Vehicle> vehicles = new ArrayList<>();

        for(Integer vehicleId: vehiclesMap) {
            if(this.vehicles[vehicleId].getType().name().equals(type.name())) {
                vehicles.add(this.vehicles[vehicleId]);
            }
        }

        return vehicles;
    }

}
