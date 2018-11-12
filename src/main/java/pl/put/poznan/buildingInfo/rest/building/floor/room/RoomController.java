package pl.put.poznan.buildingInfo.rest.building.floor.room;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.put.poznan.buildingInfo.logic.building.floor.room.RoomService;

import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {

  private static final Logger logger = LoggerFactory.getLogger(RoomController.class);

  @Autowired
  RoomService roomService;

  @RequestMapping("/area/{roomId}")
  public ResponseEntity<Float> getRoomArea(@PathVariable Integer roomId) {

    logger.debug(roomId.toString());

    Optional<Float> roomArea = roomService.getRoomArea(roomId);

    if(roomArea.isPresent()){
      return new ResponseEntity<>(roomArea.get(), HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
