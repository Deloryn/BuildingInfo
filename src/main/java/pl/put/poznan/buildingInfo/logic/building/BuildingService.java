package pl.put.poznan.buildingInfo.logic.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.put.poznan.buildingInfo.model.building.Building;
import pl.put.poznan.buildingInfo.model.building.floor.room.Room;
import pl.put.poznan.buildingInfo.logic.BuildingRepository;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * This class is a service for building operations
 */
@Component
public class BuildingService {

  @Autowired
  BuildingRepository buildingRepository;

  /**
   * Get area of the building with given id
   * @param id id of the building
   * @return area of the building
   */
  public Float calculateBuildingArea(Integer id) {

    Building buildings[] = buildingRepository.getBuildingInfo().getBuildings();
    Stream<Building> building = findBuilding(id, buildings);
    return building
            .flatMap(b -> Arrays.stream(b.getFloors()))
            .flatMap(f -> Arrays.stream(f.getRooms()))
            .map(Room::getArea)
            .reduce((float) 0, Float::sum);
  }

  /**
   * Get cube of the building with given id
   * @param id id of the building
   * @return cube of the building
   */
  public Float calculateBuildingCube(Integer id) {
    Building buildings[] = buildingRepository.getBuildingInfo().getBuildings();
    Stream<Building> building = findBuilding(id, buildings);
    return building
            .flatMap(b -> Arrays.stream(b.getFloors()))
            .flatMap(f -> Arrays.stream(f.getRooms()))
            .map(Room::getCube)
            .reduce((float) 0, Float::sum);
  }

  /**
   * Get light of the building with given id
   * @param id id of the building
   * @return light of the building
   */
  public Float calculateBuildingLight(Integer id) {
    Building buildings[] = buildingRepository.getBuildingInfo().getBuildings();
    Stream<Building> building = findBuilding(id, buildings);
    return building
            .flatMap(b -> Arrays.stream(b.getFloors()))
            .flatMap(f -> Arrays.stream(f.getRooms()))
            .map(Room::getLight)
            .reduce((float) 0, Float::sum);
  }

  /**
   * Calculate heating energy for the whole building with given id
   * @param id id of the building
   * @return heating energy of the building
   */
  public Float calculateBuildingHeating(Integer id) {
    Building buildings[] = buildingRepository.getBuildingInfo().getBuildings();
    Stream<Building> building = findBuilding(id, buildings);
    return building
            .flatMap(b -> Arrays.stream(b.getFloors()))
            .flatMap(f -> Arrays.stream(f.getRooms()))
            .map(Room::getHeating)
            .reduce((float) 0, Float::sum);
  }

  /**
   * Calculate heating energy per cube for the whole building with given id
   * @param id id of the building
   * @return heating energy per cube of the whole building
   */
  public Float calculateBuildingHeatingPerCube(Integer id) {
    Float heating = calculateBuildingHeating(id);
    Float cube = calculateBuildingCube(id);

    if(cube > 0) return heating / cube;
    else return (float) 0;
  }

    /**
     * Calculate light per area for the whole building with given id
     * @param id id of the building
     * @return light per area for the whole building with given id
     */
  public Float calculateBuildingLightPerArea(Integer id) {
    Float light = calculateBuildingLight(id);
    Float area = calculateBuildingArea(id);

    if(area > 0) return light / area;
    else return (float) 0;
  }

  /**
   * Get building object if exists
   * @param id id of the building
   * @param buildings array of buildings
   * @return building object
   */
  private Stream<Building> findBuilding(Integer id, Building[] buildings) {
    return Arrays.stream(buildings)
            .filter(b -> b.getId().equals(id));
  }
}
