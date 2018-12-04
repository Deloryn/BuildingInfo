package pl.put.poznan.buildingInfo.model;

public class Room extends Structure {

    public Room(Integer id, String name, Structure[] structures) {
        super(id, name, null);
    }

    public Room(Integer id, String name, Structure[] structures, Float area, Float cube, Float heating, Float light) {
        super(id, name, structures, area, cube, heating, light);
    }

    protected Float calculateArea() {
        return this.area;
    }

    protected Float calculateCube() {
        return this.cube;
    }

    protected Float calculateHeating() {
        return this.heating;
    }

    protected Float calculateLight() {
        return this.light;
    }
}