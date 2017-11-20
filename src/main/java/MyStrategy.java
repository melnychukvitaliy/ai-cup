import helper.Control;
import model.*;
import proxy.VehiclesInfo;

import java.util.List;

public final class MyStrategy implements Strategy {
    private VehiclesInfo vehiclesInfo;
    private Control control = new Control();

    @Override
    public void move(Player me, World world, Game game, Move move) {

        if (world.getTickIndex() == 0) {
            this.vehiclesInfo = new VehiclesInfo(world);
        } else {
            this.vehiclesInfo.updateVehicles(world.getVehicleUpdates());
        }
        List<Vehicle> arrvs = this.vehiclesInfo.getByType(
                this.vehiclesInfo.getVehiclesByPlayer().get(me.getId()),
                VehicleType.ARRV
        );
        Location centralArrv = this.control.getCentralGroup(arrvs);
        List<Vehicle> fighters = this.vehiclesInfo.getByType(
                this.vehiclesInfo.getVehiclesByPlayer().get(me.getId()),
                VehicleType.FIGHTER
        );
        Location centralFighter = this.control.getCentralGroup(fighters);

        if (world.getTickIndex() == 0) {
            move.setAction(ActionType.CLEAR_AND_SELECT);
            move.setVehicleType(VehicleType.FIGHTER);
            move.setRight(world.getWidth());
            move.setBottom(world.getHeight());
            return;
        }
        if (world.getTickIndex() == 1) {
            move.setAction(ActionType.MOVE);

            move.setX(centralArrv.getX() - centralFighter.getX());
            move.setY(centralArrv.getY() - centralFighter.getY());
        }
        
    }
}
