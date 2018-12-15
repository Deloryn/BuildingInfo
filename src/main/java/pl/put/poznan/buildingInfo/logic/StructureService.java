package pl.put.poznan.buildingInfo.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.put.poznan.buildingInfo.model.Structure;

import java.util.Optional;

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
   * @param id id of the structure
   * @return the structure object (or null if not found)
   */
  public Structure findStructure(Integer id) {
    System.out.println("czy optional");
    Optional<Structure> optionalStructure =
            structureRepository
            .getStructureInfo()
            .getStructures()
            .filter(s -> s.getId().equals(id))
            .findFirst();
    System.out.println("tak");
    if(optionalStructure.isPresent()) {
      return optionalStructure.get();
    }
    else return null;
  }

  /**
   * Get area of the structure with given id
   * @param id id of the structure
   * @return area of the structure
   */
  public Double getStructureArea(Integer id) {
    Structure structure = findStructure(id);
    if(structure != null) {
      return structure.getStructures().mapToDouble(Structure::getArea).sum();
    }
    else {
      return 0.0;
    }
  }

  /**
   * Get cube of the structure with given id
   * @param id id of the structure
   * @return cube of the structure
   */
  public Double getStructureCube(Integer id) {
    Structure structure = findStructure(id);
    if(structure != null) {
      return structure.getStructures().mapToDouble(Structure::getCube).sum();
    }
    else {
      return 0.0;
    }
  }

  /**
   * Get light of the structure with given id
   * @param id id of the structure
   * @return light of the structure
   */
  public Double getStructureLight(Integer id) {
    Structure structure = findStructure(id);
    if(structure != null) {
      return structure.getStructures().mapToDouble(Structure::getLight).sum();
    }
    else {
      return 0.0;
    }
  }

  /**
   * Get heating of the structure with given id
   * @param id id of the structure
   * @return heating of the structure
   */
  public Double getStructureHeating(Integer id) {
    Structure structure = findStructure(id);
    if(structure != null) {
      return structure.getStructures().mapToDouble(Structure::getHeating).sum();
    }
    else {
      return 0.0;
    }
  }

  /**
   * Calculate heating energy per cube for the whole structure with given id
   * @param id id of the structure
   * @return heating energy per cube of the whole structure
   */
  public Double getStructureHeatingPerCube(Integer id) {
    Double heating = getStructureHeating(id);
    Double cube = getStructureCube(id);
    if(cube > 0) return heating / cube;
    else return 0.0;
  }

    /**
     * Calculate light per area for the whole structure with given id
     * @param id id of the structure
     * @return light per area for the whole structure with given id
     */
  public Double getStructureLightPerArea(Integer id) {
    Double light = getStructureLight(id);
    Double area = getStructureArea(id);
    if(area > 0) return light / area;
    else return 0.0;
  }

  /**
   * Calculate cost for the maintenance of the structure
   * @param id id of the structure
   * @param unitPrice cost of a single light unit
   * @return the cost for the maintenance of the structure
   */
  public Double getMaintenanceCost(Integer id, Double unitPrice) {
    if(unitPrice == null) unitPrice = 0.0;
    Double light = getStructureLight(id);
    return light * unitPrice;
  }

}
