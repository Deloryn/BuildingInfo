package pl.put.poznan.buildingInfo.model;

import java.util.ArrayList;

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
  private ArrayList<Structure> structures;
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
   *
   * @param id         id of the structure
   * @param name       name of the structure
   * @param structures children structures of the structure
   * @param area       area of the structure
   * @param cube       cube of the structure
   * @param heating    heating of the structure
   * @param light      light of the structure
   */
  public Structure(Integer id, String name, ArrayList<Structure> structures, Double area, Double cube, Double heating, Double light) {
    this.id = id;
    this.name = name;
    this.structures = structures;
    this.area = area;
    this.cube = cube;
    this.heating = heating;
    this.light = light;
  }

  /**
   * Based on recursion function that returns structure by id
   *
   * @return structure with given id or null if neither this structure nor any of its descendant has given id
   */
  public Structure getStructureById(Integer id) {
    if (id.equals(this.id)) {
      return this;
    }

    if (structures != null) {
      for (Structure structure : structures) {
        Structure nestedStructure = structure.getStructureById(id);
        if (nestedStructure != null) {
          return nestedStructure;
        }
      }
    }

    return null;
  }

  /**
   * Based on recursion function that removes structure by id
   *
   * @return true on success, false on failure
   */
  public Boolean removeStructureById(Integer id) {
    if (structures != null) {
      for (Structure structure : structures) {
        if (id.equals(structure.id)) {
          structures.remove(structure);
          return true;
        }
        if (structure.removeStructureById(id)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Getter of id
   *
   * @return id of the structure or -1 if it doesn't have one
   */
  public Integer getId() {
    return this.id;
  }

  /**
   * Getter of name
   *
   * @return name of the structure (or null if it doesn't have one)
   */
  public String getName() {
    return this.name;
  }

  /**
   * Getter of structures
   *
   * @return an array of children structures
   */
  public ArrayList<Structure> getStructures() {
    return structures;
  }

  /**
   * Getter of area
   *
   * @return total area of the structure, including nested structures
   */
  public Double getArea() {
    if (this.structures == null) {
      return this.area;
    }

    return structures.stream().map(Structure::getArea).reduce(0.0, Double::sum);
  }

  /**
   * Getter of cube
   *
   * @return total area of the structure, including nested structures
   */
  public Double getCube() {
    if (this.structures == null) {
      return this.cube;
    }

    return structures.stream().map(Structure::getCube).reduce(0.0, Double::sum);
  }

  /**
   * Getter of heating
   *
   * @return total heating of the structure, including nested structures
   */
  public Double getHeating() {
    if (this.structures == null) {
      return this.heating;
    }

    return structures.stream().map(Structure::getHeating).reduce(0.0, Double::sum);
  }

  /**
   * Getter of light
   *
   * @return total light of the structure, including nested structures
   */
  public Double getLight() {
    if (this.structures == null) {
      return this.light;
    }

    return structures.stream().map(Structure::getLight).reduce(0.0, Double::sum);
  }
}
