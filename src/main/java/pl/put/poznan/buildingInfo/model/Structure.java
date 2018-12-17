package pl.put.poznan.buildingInfo.model;

import java.util.stream.Stream;

/**
 * A class for any type of structure - for buildings, floors, rooms, etc.
 * It's designed in a way that allows to operate on its children (composite)
 */
public class Structure {
    /**
     * ID of the structure (it is used to identify the structure in requests)
     */
    private Integer id;
    /**
     * Name of the structure
     */
    private String name;
    /**
     * An array of its children structures
     */
    private Structure[] structures;
    /**
     * Area of the structure
     */
    private Double area;
    /**
     * The volume/capacity of the structure (for example for cube it is width*length*height)
     */
    private Double cube;
    /**
     * The energy consumption for the structure heating
     */
    private Double heating;
    /**
     * The lighting power value for the structure
     */
    private Double light;

    /**
     * A constructor for Structure class
     * @param id id of the structure
     * @param name name of the structure
     * @param structures children structures of the structure
     * @param area area of the structure
     * @param cube cube of the structure
     * @param heating heating of the structure
     * @param light light of the structure
     */
    public Structure(Integer id, String name, Structure[] structures, Double area, Double cube, Double heating, Double light) {
        this.id = id;
        this.name = name;
        this.structures = structures;
        this.area = area;
        this.cube = cube;
        this.heating = heating;
        this.light = light;
    }

    /**
     * Getter of id
     * @return id of the structure or -1 if it doesn't have one
     */
    public Integer getId() {
        if(this.id == null) {
            return -1;
        }
        return this.id;
    }

    /**
     * Getter of name
     * @return name of the structure (or null if it doesn't have one)
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter of structures (this structure and all its children)
     * @return a stream containing this structure and all its children (flatmapped into single stream)
     */
    public Stream<Structure> getStructures() {
        if(this.structures == null) {
            return Stream.of(this);
        }
        else {
            return Stream.concat(Stream.of(this), Stream.of(this.structures).flatMap(Structure::getStructures));
        }
    }

    /**
     * Getter of area
     * @return area of the structure or 0.0 if it doesn't have one
     */
    public Double getArea() {
        if(this.area == null) {
            return 0.0;
        }
        return this.area;
    }

    /**
     * Getter of cube
     * @return cube of the structure or 0.0 if it doesn't have one
     */
    public Double getCube() {
        if(this.cube == null) {
            return 0.0;
        }
        else return this.cube;
    }

    /**
     * Getter of heating
     * @return heating of the structure or 0.0 if it doesn't have one
     */
    public Double getHeating() {
        if(this.heating == null) {
            return 0.0;
        }
        else return this.heating;
    }

    /**
     * Getter of light
     * @return light of the structure or 0.0 if it doesn't have one
     */
    public Double getLight() {
        if(this.light == null) {
            return 0.0;
        }
        else return this.light;
    }
}
