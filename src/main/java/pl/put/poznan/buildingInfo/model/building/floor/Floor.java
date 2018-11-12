package pl.put.poznan.buildingInfo.model.building.floor;

import lombok.Data;
import pl.put.poznan.buildingInfo.model.building.floor.room.Room;

@Data
public class Floor {

  private Integer id;
  private String name;
  private Room rooms[];
}
