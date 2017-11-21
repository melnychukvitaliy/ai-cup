import com.sun.javaws.exceptions.InvalidArgumentException;
import helper.Control;
import helper.GroupsBuilder;
import model.*;
import proxy.VehiclesInfo;

import java.util.List;

import static sun.misc.Version.print;

public final class MyStrategy implements Strategy {
    private VehiclesInfo vehiclesInfo;
    private Control control = new Control();
    private GroupsBuilder groupsBuilder = new GroupsBuilder();

    @Override
    public void move(Player me, World world, Game game, Move move) {

        if (world.getTickIndex() == 0) {
            this.vehiclesInfo = new VehiclesInfo(world);
        } else {
            if (world.getVehicleUpdates() != null) {
                this.vehiclesInfo.updateVehicles(world.getVehicleUpdates());
            }
        }
        List<Vehicle> arrvs = this.vehiclesInfo.getByType(
                this.vehiclesInfo.getVehiclesByPlayer().get(me.getId()),
                VehicleType.ARRV
        );
        List<Vehicle> tanks = this.vehiclesInfo.getByType(
                this.vehiclesInfo.getVehiclesByPlayer().get(me.getId()),
                VehicleType.TANK
        );
        List<Vehicle> ifvs = this.vehiclesInfo.getByType(
                this.vehiclesInfo.getVehiclesByPlayer().get(me.getId()),
                VehicleType.IFV
        );
        Location centralTank = this.control.getCentralGroup(tanks);
        Location centraIfv = this.control.getCentralGroup(ifvs);
        Location centralArrv = this.control.getCentralGroup(arrvs);
        boolean isXFlang = groupsBuilder.isXFlang(centralTank, centraIfv, centralArrv);
        int sectorId = 0;

        try {
            if (isXFlang) {
                sectorId = groupsBuilder.getFreeSector(centralTank.getX(), centraIfv.getX(), centralArrv.getX());
            } else {
                sectorId = groupsBuilder.getFreeSector(centralTank.getY(), centraIfv.getY(), centralArrv.getY());
            }
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }
        if (world.getTickIndex() == 0) {
            move.setAction(ActionType.CLEAR_AND_SELECT);
            move.setVehicleType(VehicleType.FIGHTER);
            move.setRight(world.getWidth());
            move.setBottom(world.getHeight());
            return;
        }
//        if (world.getTickIndex() == 1) {
//            move.setAction(ActionType.MOVE);
//
//            move.setX(centralArrv.getX() - centralFighter.getX());
//            move.setY(centralArrv.getY() - centralFighter.getY());
//        }

    }
}
