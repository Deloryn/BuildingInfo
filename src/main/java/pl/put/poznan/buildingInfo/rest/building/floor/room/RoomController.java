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
    logger.info("Get room area. Room id: ", roomId);

    Optional<Float> roomArea = roomService.getRoomArea(roomId);

    if(roomArea.isPresent()){
      return new ResponseEntity<>(roomArea.get(), HttpStatus.OK);
    }
    else {
      logger.info("room with given id does not exist. Returning not found");
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping("/cube/{roomId}")
  public ResponseEntity<Float> getRoomCube(@PathVariable Integer roomId) {

    logger.debug(roomId.toString());
    logger.info("Get room cube. Room id: ", roomId);

    Optional<Float> roomCube = roomService.getRoomCube(roomId);

    if(roomCube.isPresent()){
      return new ResponseEntity<>(roomCube.get(), HttpStatus.OK);
    }
    else {
      logger.info("room with given id does not exist. Returning not found");
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping("/light/{roomId}")
  public ResponseEntity<Float> getRoomLight(@PathVariable Integer roomId) {

    logger.debug(roomId.toString());
    logger.info("Get room light. Room id: ", roomId);

    Optional<Float> roomLight = roomService.getRoomLight(roomId);

    if(roomLight.isPresent()){
      return new ResponseEntity<>(roomLight.get(), HttpStatus.OK);
    }
    else {
      logger.info("room with given id does not exist. Returning not found");
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping("/heating/{roomId}")
  public ResponseEntity<Float> getRoomHeating(@PathVariable Integer roomId) {

    logger.debug(roomId.toString());
    logger.info("Get room heating. Room id: ", roomId);

    Optional<Float> roomHeating = roomService.getRoomHeating(roomId);

    if(roomHeating.isPresent()){
      return new ResponseEntity<>(roomHeating.get(), HttpStatus.OK);
    }
    else {
      logger.info("room with given id does not exist. Returning not found");
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping("/heating-per-cube/{roomId}")
  public ResponseEntity<Float> getRoomHeatingPerCube(@PathVariable Integer roomId) {

    logger.debug(roomId.toString());
    logger.info("Get room heating per cube. Room id: ", roomId);

    Optional<Float> roomHeatingPerCube = roomService.getRoomHeatingPerCube(roomId);

    if(roomHeatingPerCube.isPresent()){
      return new ResponseEntity<>(roomHeatingPerCube.get(), HttpStatus.OK);
    }
    else {
      logger.info("room with given id does not exist. Returning not found");
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping("/light-per-area{roomId}")
  public ResponseEntity<Float> getRoomLightPerArea(@PathVariable Integer roomId) {

    logger.debug(roomId.toString());
    logger.info("Get room light per area. Room id: ", roomId);

    Optional<Float> roomLightPerArea = roomService.getRoomLightPerArea(roomId);

    if(roomLightPerArea.isPresent()){
      return new ResponseEntity<>(roomLightPerArea.get(), HttpStatus.OK);
    }
    else {
      logger.info("room with given id does not exist. Returning not found");
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
