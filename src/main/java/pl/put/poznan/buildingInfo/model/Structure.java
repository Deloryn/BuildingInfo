package pl.put.poznan.buildingInfo.model;

public class Structure {
    protected Integer id;
    protected String name;
    protected Structure[] structures;
    protected Float area;
    protected Float cube;
    protected Float heating;
    protected Float light;

    public Structure(Integer id, String name, Structure[] structures) {
        this.id = id;
        this.name = name;
        this.structures = structures;
        this.area = 0f;
        this.cube = 0f;
        this.heating = 0f;
        this.light = 0f;
    }

    public Structure(Integer id, String name, Structure[] structures, Float area, Float cube, Float heating, Float light) {
        this.id = id;
        this.name = name;
        this.structures = structures;
        this.area = area;
        this.cube = cube;
        this.heating = heating;
        this.light = light;
    }

    protected Float calculateArea() {
        Float total = 0f;
        for(Structure structure : this.structures) {
            total += structure.getArea();
        }
        return total;
    }

    protected Float calculateCube() {
        Float total = 0f;
        for(Structure structure : this.structures) {
            total += structure.getCube();
        }
        return total;
    }

    protected Float calculateHeating() {
        Float total = 0f;
        for(Structure structure : this.structures) {
            total += structure.getHeating();
        }
        return total;
    }

    protected Float calculateLight() {
        Float total = 0f;
        for(Structure structure : this.structures) {
            total += structure.getLight();
        }
        return total;
    }

    public Float getArea() {
        this.area = this.calculateArea();
        return this.area;
    }
    public Float getCube() {
        this.cube = this.calculateCube();
        return this.cube;
    }
    public Float getHeating() {
        this.heating = this.calculateHeating();
        return this.heating;
    }
    public Float getLight() {
        this.light = this.calculateLight();
        return this.light;
    }
}
