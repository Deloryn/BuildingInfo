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

  @Autowired
  StructureRepository structureRepository;

  /**
   * Get area of the structure with given id
   * @param id id of the structure
   * @return area of the structure
   */
  public Float getStructureArea(Integer id) {
    Optional<Structure> optionalStructure = structureRepository.getStructureInfo().findStructure(id).findFirst();
    if(optionalStructure.isPresent()) {
      return optionalStructure.get().getArea();
    }
    else {
      return 0f;
    }
  }

  /**
   * Get cube of the structure with given id
   * @param id id of the structure
   * @return cube of the structure
   */
  public Float getStructureCube(Integer id) {
    Optional<Structure> optionalStructure = structureRepository.getStructureInfo().findStructure(id).findFirst();
    if(optionalStructure.isPresent()) {
      return optionalStructure.get().getCube();
    }
    else {
      return 0f;
    }
  }

  /**
   * Get light of the structure with given id
   * @param id id of the structure
   * @return light of the structure
   */
  public Float getStructureLight(Integer id) {
    Optional<Structure> optionalStructure = structureRepository.getStructureInfo().findStructure(id).findFirst();
    if(optionalStructure.isPresent()) {
      return optionalStructure.get().getLight();
    }
    else {
      return 0f;
    }
  }

  /**
   * Get heating of the structure with given id
   * @param id id of the structure
   * @return heating of the structure
   */
  public Float getStructureHeating(Integer id) {
    Optional<Structure> optionalStructure = structureRepository.getStructureInfo().findStructure(id).findFirst();
    if(optionalStructure.isPresent()) {
      return optionalStructure.get().getHeating();
    }
    else {
      return 0f;
    }
  }

  /**
   * Calculate heating energy per cube for the whole structure with given id
   * @param id id of the structure
   * @return heating energy per cube of the whole structure
   */
  public Float getStructureHeatingPerCube(Integer id) {
    Float heating = getStructureHeating(id);
    Float cube = getStructureCube(id);
    if(cube > 0) return heating / cube;
    else return 0f;
  }

    /**
     * Calculate light per area for the whole structure with given id
     * @param id id of the structure
     * @return light per area for the whole structure with given id
     */
  public Float getStructureLightPerArea(Integer id) {
    Float light = getStructureLight(id);
    Float area = getStructureArea(id);
    if(area > 0) return light / area;
    else return 0f;
  }

}
