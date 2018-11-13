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
   * Get light of the room with given id
   * @param id id of the room
   * @return light of the room
   */
  public Optional<Float> getRoomLight(Integer id) {
    Optional<Room> room = findRoom(id);
    return room.map(Room::getLight);
  }

  /**
   * Get heating energy of the room with given id
   * @param id id of the room
   * @return heating energy of the room
   */
  public Optional<Float> getRoomHeating(Integer id) {
    Optional<Room> room = findRoom(id);
    return room.map(Room::getHeating);
  }

  /**
   * Get heating energy per cube in the room with given id
   * @param id id of the room
   * @return heating energy per cube in the room
   */
  public Optional<Float> getRoomHeatingPerCube(Integer id) {
    Optional<Room> room = findRoom(id);
    if(room.isPresent()) {
      return Optional.of(room.get().getHeating() / room.get().getCube());
    }
    else return Optional.empty();
  }

    /**
     * Get light per area for the room with given id
     * @param id id of the room
     * @return light per area for the room
     */
  public Optional<Float> getRoomLightPerArea(Integer id) {
    Optional<Room> room = findRoom(id);
    if(room.isPresent()) {
        return Optional.of(room.get().getLight() / room.get().getArea());
    }
    else return Optional.empty();
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
