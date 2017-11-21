package helper;

import com.sun.javaws.exceptions.InvalidArgumentException;
import model.Location;

import java.util.HashMap;
import java.util.List;

public class GroupsBuilder {

    public boolean isXFlang(Location a, Location b, Location c) {
        return (Math.abs(a.getX() - b.getX()) <= 2)
                || (Math.abs(a.getX() - c.getX()) <= 2)
                || (Math.abs(b.getX() - c.getX()) <= 2);
    }


    public int getFreeSector(int a, int b, int c) throws InvalidArgumentException {
        Integer[] sectors = {1,2,3};

        HashMap< Integer, Integer> usedSectors = new HashMap<>();

        usedSectors.putIfAbsent(this.getSector(a), 1);
        usedSectors.putIfAbsent(this.getSector(b), 1);
        usedSectors.putIfAbsent(this.getSector(c), 1);

        for(Integer sector: sectors) {
            if(usedSectors.get(sector) == null) {
                return sector;
            }
        }

        return 0;
    }

    public int getSector(int a) throws InvalidArgumentException {
        return (Math.abs(a - this.getCoordsBySectorId(1)) <= 2)
                ? 1 : (Math.abs(a - this.getCoordsBySectorId(2)) <= 2) ? 2 : 3;
    }

    public int getCoordsBySectorId(int a) throws InvalidArgumentException {
        switch (a) {
            case 1:
                return 45;
            case 2:
                return 144;
            case 3:
                return 193;
            default:
                throw new InvalidArgumentException(new String[]{"SectorId is not valid"});
        }
    }
}
