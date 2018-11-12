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
   * Calculate heating energy cost for the whole building with given id
   * @param id id of the building
   * @return heating energy cost of the building
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
