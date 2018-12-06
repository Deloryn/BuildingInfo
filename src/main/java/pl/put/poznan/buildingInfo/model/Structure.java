package pl.put.poznan.buildingInfo.model;

import java.util.stream.Stream;

public class Structure {
    private Integer id;
    private String name;
    private Structure[] structures;
    private Double area;
    private Double cube;
    private Double heating;
    private Double light;

    public Integer getId() {
        if(this.id == null) {
            return -1;
        }
        return this.id;
    }

    public Stream<Structure> getStructures() {
        if(this.structures == null) {
            return Stream.of(this);
        }
        else {
            return Stream.concat(Stream.of(this), Stream.of(this.structures).flatMap(Structure::getStructures));
        }
    }

    public Double getArea() {
        if(this.area == null) {
            return 0.0;
        }
        return this.area;
    }

    public Double getCube() {
        if(this.cube == null) {
            return 0.0;
        }
        else return this.cube;
    }

    public Double getHeating() {
        if(this.heating == null) {
            return 0.0;
        }
        else return this.heating;
    }

    public Double getLight() {
        if(this.light == null) {
            return 0.0;
        }
        else return this.light;
    }
}
