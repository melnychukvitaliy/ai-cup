package storage;

import model.Vehicle;
import model.VehicleUpdate;

import java.util.Hashtable;

public class VehiclesInfo {
    public Vehicle[] vehicles;

    Hashtable<Long,Integer> hm=new Hashtable<Long,Integer>();

    public VehiclesInfo(Vehicle[] newVehicles) {
        int i = 0;
        while(i < newVehicles.length - 1) {
            hm.put(newVehicles[i].getId(), i);
            i++;
        }
        this.vehicles = newVehicles;
    }


    public void updateVehicles(VehicleUpdate[] vehicleUpdate) {
        int i;
        for(i = 0;i < vehicleUpdate.length - 1; i++) {
            int id = hm.get(vehicleUpdate[i].getId());

            this.vehicles[id] = new Vehicle(this.vehicles[i], vehicleUpdate[i]);
        }
    }

    public Vehicle[] getVehicles() {
        return vehicles;
    }
}
