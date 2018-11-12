package pl.put.poznan.buildingInfo.logic.building.floor.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.put.poznan.buildingInfo.logic.BuildingRepository;
import pl.put.poznan.buildingInfo.model.building.Building;
import pl.put.poznan.buildingInfo.model.building.floor.room.Room;

import java.util.Arrays;
import java.util.Optional;

@Component
public class RoomService {

  @Autowired
  BuildingRepository buildingRepository;

  public Optional<Float> getRoomArea(Integer id) {

    Optional<Room> room = findRoom(id);
    return room.map(Room::getArea);
  }

  private Optional<Room> findRoom(Integer id){

    Building[] buildings = buildingRepository.getBuildingInfo().getBuildings();
    return Arrays.stream(buildings)
            .flatMap(b -> Arrays.stream(b.getFloors()))
            .flatMap(f -> Arrays.stream(f.getRooms()))
            .filter(r -> r.getId().equals(id))
            .findFirst();
  }
}
