package pl.put.poznan.buildingInfo.logic.building.floor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.put.poznan.buildingInfo.logic.BuildingRepository;
import pl.put.poznan.buildingInfo.model.building.Building;
import pl.put.poznan.buildingInfo.model.building.floor.Floor;
import pl.put.poznan.buildingInfo.model.building.floor.room.Room;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * This class is a service for floor operations
 */
@Component
public class FloorService {

  @Autowired
  BuildingRepository buildingRepository;

  /**
   * Get area of the whole floor with given id
   * @param id id of the floor
   * @return area of the floor
   */
  public Float calculateFloorArea(Integer id) {

    Stream<Floor> room = findFloor(id);
    return room.flatMap(f -> Arrays.stream(f.getRooms()))
            .map(Room::getArea)
            .reduce((float) 0, Float::sum);
  }

  /**
   * Get cube of the whole floor with given id
   * @param id id of the floor
   * @return cube of the floor
   */
  public Float calculateFloorCube(Integer id) {
    Stream<Floor> room = findFloor(id);
    return room.flatMap(f -> Arrays.stream(f.getRooms()))
            .map(Room::getCube)
            .reduce((float) 0, Float::sum);
  }

  /**
   * Get light of the whole floor with given id
   * @param id id of the floor
   * @return light of the floor
   */
  public Float calculateFloorLight(Integer id) {
    Stream<Floor> room = findFloor(id);
    return room.flatMap(f -> Arrays.stream(f.getRooms()))
            .map(Room::getLight)
            .reduce((float) 0, Float::sum);
  }

  /**
   * Get heating energy for the whole floor with given id
   * @param id id of the floor
   * @return heating energy for the whole floor
   */
  public Float calculateFloorHeating(Integer id) {
    Stream<Floor> room = findFloor(id);
    return room.flatMap(f -> Arrays.stream(f.getRooms()))
            .map(Room::getHeating)
            .reduce((float) 0, Float::sum);
  }

  /**
   * Get heating energy per cube for the whole floor with given id
   * @param id id of the floor
   * @return heating energy per cube for the whole floor
   */
  public Float calculateFloorHeatingPerCube(Integer id) {
    Float heating = calculateFloorHeating(id);
    Float cube = calculateFloorCube(id);

    if(cube > 0) return heating / cube;
    else return (float) 0;
  }

  /**
   * Get light per area for the whole floor with given id
   * @param id id of the floor
   * @return light per area for the whole floor
   */
  public Float calculateFloorLightPerArea(Integer id) {
    Float light = calculateFloorLight(id);
    Float area = calculateFloorArea(id);

    if(area > 0) return light / area;
    else return (float) 0;
  }

  /**
   * Get floor object if exists
   * @param id id of the floor
   * @return floor object
   */
  private Stream<Floor> findFloor(Integer id) {

    Building[] buildings = buildingRepository.getBuildingInfo().getBuildings();
    return Arrays.stream(buildings)
            .flatMap(b -> Arrays.stream(b.getFloors()))
            .filter(r -> r.getId().equals(id));
  }
}
