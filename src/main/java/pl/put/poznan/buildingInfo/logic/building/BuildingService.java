package pl.put.poznan.buildingInfo.logic.building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.put.poznan.buildingInfo.model.building.Building;
import pl.put.poznan.buildingInfo.model.building.floor.room.Room;
import pl.put.poznan.buildingInfo.logic.BuildingRepository;

import java.util.Arrays;
import java.util.stream.Stream;

@Component
public class BuildingService {

  @Autowired
  BuildingRepository buildingRepository;

  public Float calculateBuildingArea(Integer id) {

    Building buildings[] = buildingRepository.getBuildingInfo().getBuildings();
    Stream<Building> building = findBuilding(id, buildings);
    return building
            .flatMap(b -> Arrays.stream(b.getFloors()))
            .flatMap(f -> Arrays.stream(f.getRooms()))
            .map(Room::getArea)
            .reduce((float) 0, Float::sum);
  }

  private Stream<Building> findBuilding(Integer id, Building[] buildings) {
    return Arrays.stream(buildings)
            .filter(b -> b.getId().equals(id));
  }
}
