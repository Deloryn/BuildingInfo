package pl.put.poznan.buildingInfo.model.building;

import lombok.Data;
import pl.put.poznan.buildingInfo.model.building.floor.Floor;

@Data
public class Building {

  private Integer id;
  private String name;
  private Floor floors[];
}
