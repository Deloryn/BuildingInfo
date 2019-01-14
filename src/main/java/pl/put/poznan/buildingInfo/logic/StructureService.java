package pl.put.poznan.buildingInfo.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.put.poznan.buildingInfo.model.Structure;

import java.util.ArrayList;

/**
 * This class is a service for structure operations
 */
@Component
public class StructureService {

  /**
   * structureRepository is an instance of the class StructureRepository,
   * which is a broker used in JSON deserialization
   */
  @Autowired
  StructureRepository structureRepository;

  /**
   * Get all structures
   *
   * @return an array of all structures
   */
  public ArrayList<Structure> getAllStructures() {
    return structureRepository.getStructureInfo().getStructures();
  }

  /**
   * Find the structure with given id
   *
   * @param id id of the structure
   * @return the structure object (or null if not found)
   */
  public Structure findStructure(Integer id) {
    return structureRepository.getStructureInfo().getStructureById(id);
  }

  /**
   * Remove the structure with given id
   *
   * @param id id of the structure
   */
  public Boolean removeStructure(Integer id) {
    return structureRepository.getStructureInfo().removeStructureById(id);
  }

  /**
   * Get area of the structure with given id
   *
   * @param id id of the structure
   * @return area of the structure
   */
  public Double getStructureArea(Integer id) {
    Structure structure = findStructure(id);
    if(structure == null) return 0.0;
    else return structure.getArea();
  }

  /**
   * Get cube of the structure with given id
   *
   * @param id id of the structure
   * @return cube of the structure
   */
  public Double getStructureCube(Integer id) {
    Structure structure = findStructure(id);
    if(structure == null) return 0.0;
    else return structure.getCube();
  }

  /**
   * Get light of the structure with given id
   *
   * @param id id of the structure
   * @return light of the structure
   */
  public Double getStructureLight(Integer id) {
    Structure structure = findStructure(id);
    if(structure == null) return 0.0;
    else return structure.getLight();
  }

  /**
   * Get heating of the structure with given id
   *
   * @param id id of the structure
   * @return heating of the structure
   */
  public Double getStructureHeating(Integer id) {
    Structure structure = findStructure(id);
    if(structure == null) return 0.0;
    else return structure.getHeating();
  }

  /**
   * Calculate heating energy per cube for the whole structure with given id
   *
   * @param id id of the structure
   * @return heating energy per cube of the whole structure
   */
  public Double getStructureHeatingPerCube(Integer id) {
    Double heating = getStructureHeating(id);
    Double cube = getStructureCube(id);
    if (cube > 0) return heating / cube;
    else return 0.0;
  }

  /**
   * Calculate light per area for the whole structure with given id
   *
   * @param id id of the structure
   * @return light per area for the whole structure with given id
   */
  public Double getStructureLightPerArea(Integer id) {
    Double light = getStructureLight(id);
    Double area = getStructureArea(id);
    if (area > 0) return light / area;
    else return 0.0;
  }

  /**
   * Calculate cost for the maintenance of the structure
   *
   * @param id        id of the structure
   * @param unitPrice cost of a single light unit
   * @return the cost for the maintenance of the structure
   */
  public Double getMaintenanceCost(Integer id, Double unitPrice) {
    if (unitPrice == null) unitPrice = 0.0;
    Double light = getStructureLight(id);
    return light * unitPrice;
  }

}
