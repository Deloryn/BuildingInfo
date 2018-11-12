package pl.put.poznan.buildingInfo.logic.building.floor.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.put.poznan.buildingInfo.logic.BuildingRepository;
import pl.put.poznan.buildingInfo.model.building.Building;
import pl.put.poznan.buildingInfo.model.building.floor.room.Room;

import java.util.Arrays;
import java.util.Optional;

/**
 * This class is a service for room operations
 */
@Component
public class RoomService {

  @Autowired
  BuildingRepository buildingRepository;

  /**
   * Get area of the room with given id
   * @param id id of the room
   * @return area of the room
   */
  public Optional<Float> getRoomArea(Integer id) {

    Optional<Room> room = findRoom(id);
    return room.map(Room::getArea);
  }

  /**
   * Get cube of the room with given id
   * @param id id of the room
   * @return cube of the room
   */
  public Optional<Float> getRoomCube(Integer id) {
    Optional<Room> room = findRoom(id);
    return room.map(Room::getCube);
  }

  /**
   * Get heating energy cost of the room with given id
   * @param id id of the room
   * @return heating energy cost of the room
   */
  public Optional<Float> getRoomHeating(Integer id) {
    Optional<Room> room = findRoom(id);
    return room.map(Room::getHeating);
  }

  /**
   * Get room object if exists
   * @param id id of the room
   * @return room object
   */
  private Optional<Room> findRoom(Integer id){

    Building[] buildings = buildingRepository.getBuildingInfo().getBuildings();
    return Arrays.stream(buildings)
            .flatMap(b -> Arrays.stream(b.getFloors()))
            .flatMap(f -> Arrays.stream(f.getRooms()))
            .filter(r -> r.getId().equals(id))
            .findFirst();
  }
}
