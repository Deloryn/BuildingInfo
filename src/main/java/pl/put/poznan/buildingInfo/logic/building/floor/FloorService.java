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
