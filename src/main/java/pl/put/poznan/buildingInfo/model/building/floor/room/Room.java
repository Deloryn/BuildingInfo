package pl.put.poznan.buildingInfo.model.building.floor.room;

import lombok.Data;

@Data
public class Room {

  private Integer id;
  private String name;
  private Float area;
  private Float cube;
  private Float heating;
  private Float light;
}
