package pl.put.poznan.buildingInfo.model;

public class Building extends Structure {
    public Building(Integer id, String name, Structure[] structures) {
        super(id, name, structures);
    }

    public Building(Integer id, String name, Structure[] structures, Float area, Float cube, Float heating, Float light) {
        super(id, name, structures, area, cube, heating, light);
    }
}
