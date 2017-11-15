import model.*;
import storage.VehiclesInfo;

public final class MyStrategy implements Strategy {
    private VehiclesInfo vehiclesInfo;
    @Override
    public void move(Player me, World world, Game game, Move move) {

        if(world.getTickIndex() == 0) {
            this.vehiclesInfo = new VehiclesInfo(world.getNewVehicles());
        } else {
            this.vehiclesInfo.updateVehicles(world.getVehicleUpdates());
        }

//        if (world.getTickIndex() == 0) {
//            move.setAction(ActionType.CLEAR_AND_SELECT);
//            move.setVehicleType(VehicleType.HELICOPTER);
//            move.setRight(world.getWidth());
//            move.setBottom(world.getHeight());
//            return;
//        }
//
//        if(world.getTickIndex() == 1) {
//            move.setAction(ActionType.ADD_TO_SELECTION);
//            move.setVehicleType(VehicleType.FIGHTER);
//            move.setRight(world.getWidth());
//            move.setBottom(world.getHeight());
//            return;
//        }
//        if (world.getTickIndex() == 4) {
//            move.setAction(ActionType.ASSIGN);
//            move.setGroup(1);
//        }
//        if (world.getTickIndex() == 5) {
//            move.setAction(ActionType.MOVE);
//            move.setX(world.getWidth());
//            move.setY(0);
//        }
    }
}
