package pl.put.poznan.buildingInfo.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    //private List <Structure> structures= new ArrayList<>();
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
     * Getter of structures (this structure and all its children)
     * @return a stream containing this structure and all its children (flatmapped into single stream)
     */
    public Stream<Structure> getStructures() {
        if(this.structures == null) {
            return Stream.of(this);
        }
        else {
            //Structure [] structureTab = new Structure [this.structures.size()];
            //structures.toArray(structureTab);
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*public void setStructures(Structure[] structures) {
        this.structures = structures;
    }*/

    public void setArea(Double area) {
        this.area = area;
    }

    public void setCube(Double cube) {
        this.cube = cube;
    }

    public void setHeating(Double heating) {
        this.heating = heating;
    }

    public void setLight(Double light) {
        this.light = light;
    }

    public Structure() {
        this.structures = null;
    }

    public Structure(Integer id, String name, Double area, Double cube, Double heating, Double light) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.cube = cube;
        this.heating = heating;
        this.light = light;
        this.structures = null;
    }

    public String getName() {
        return name;
    }

    public void addStructure(Structure structure){
        System.out.println("imie tej: " + structure.getName());
        System.out.println("klasa structures " + this.structures.getClass().getName());
        System.out.println(this.structures[0].getName());

        ArrayList<Structure> structureList = new ArrayList<Structure>(Arrays.asList(this.structures));

        for(Structure s : structureList){
            System.out.println("structura: " + s.getId() + " " + s.getName());
        }

        System.out.println("wilkosc structures: " + this.structures.length);
        System.out.println("wilkosc structureList: " + structureList.size());
        System.out.println("klasa structureList " + structureList.getClass().getName());
        System.out.println("klasa structures " + this.structures.getClass().getName());
        System.out.println("klasa structure " + structure.getClass().getName());

        structureList.add(structure);

        for(Structure s : structureList){
            System.out.println("structura: " + s.getId() + " " + s.getName());
        }

        System.out.println("dodalismy");
        System.out.println("wilkosc: " + structureList.size());

        Structure [] tabOfstructures = new Structure [structureList.size()];
        structureList.toArray(tabOfstructures);
        this.structures = tabOfstructures;
        System.out.println("wilkosc: " + this.structures.length);

        System.out.println("takie imie: " + this.structures[2].getId());

    }

    public void showNumberStructure(){
        System.out.println("jest ich " + this.structures.length);
        System.out.println("clasa ich " + this.structures.getClass().getName());
    }

    public Structure getStructureByIndex(int i){
        return this.structures[i];
    }

    public void deleteStructure(){

    }



    /*public Structure[] getStructures_1 (){
        return structures;
    }

    public void addSpecialChild (Structure structure){
        this.structures[2] = structure;
    }*/
}