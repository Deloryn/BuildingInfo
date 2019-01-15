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
   * Find the structure with given id
   *
   * @param id id of the structure
   * @return the structure object (or null if not found)
   */
  public Structure findStructure(Integer id) {
    return structureRepository.getStructureInfo().getStructureById(id);
  }

  /**
   * Delete the structure with given id
   *
   * @param id id of the structure
   */
  public Boolean deleteStructure(Integer id) {
    Structure structureInfo = structureRepository.getStructureInfo();
    Boolean wasSuccessful = structureInfo.deleteStructureById(id);
    if (wasSuccessful) {
      structureRepository.saveStructureInfo(structureInfo);
    }
    return wasSuccessful;
  }

  /**
   * Get area of the structure with given id
   *
   * @param id id of the structure
   * @return area of the structure
   */
  public Double getStructureArea(Integer id) {
    Structure structure = findStructure(id);
    if (structure == null) return 0.0;
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
    if (structure == null) return 0.0;
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
    if (structure == null) return 0.0;
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
    if (structure == null) return 0.0;
    else return structure.getHeating();
  }

  /**
   * Get heating energy per cube for the whole structure with given id
   *
   * @param id id of the structure
   * @return heating energy per cube of the whole structure
   */
  public Double getStructureHeatingPerCube(Integer id) {
    Structure structure = findStructure(id);
    if (structure == null) return 0.0;
    else return structure.getStructureHeatingPerCube();
  }

  /**
   * Get light per area for the whole structure with given id
   *
   * @param id id of the structure
   * @return light per area for the whole structure with given id
   */
  public Double getStructureLightPerArea(Integer id) {
    Structure structure = findStructure(id);
    if (structure == null) return 0.0;
    else return structure.getStructureLightPerArea();
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

  /**
   * Get structures with rooms with heating per cube higher than given
   *
   * @param threshold given heating per cube value
   * @return structures with rooms with heating per cube higher than threshold
   */
  public ArrayList<Structure> getStructuresWithHeatingPerCubeExceeded(Double threshold) {
    Structure foundStructure = structureRepository.getStructureInfo().getStructuresWithHeatingPerCubeExceeded(threshold);
    if(foundStructure != null){
      return foundStructure.getStructures();
    }
    return new ArrayList<>();
  }
}
