package pl.put.poznan.buildingInfo.rest.building.floor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.put.poznan.buildingInfo.logic.building.floor.FloorService;

@RestController
@RequestMapping("/floor")
public class FloorController {

  private static final Logger logger = LoggerFactory.getLogger(FloorController.class);

  @Autowired
  FloorService floorService;

  @RequestMapping("/area/{floorId}")
  public ResponseEntity<Float> getFloorArea(@PathVariable Integer floorId) {

    logger.debug(floorId.toString());
    logger.info("Get floor area. Floord id: ", floorId.toString());

    Float totalArea = floorService.calculateFloorArea(floorId);
    return new ResponseEntity<>(totalArea, HttpStatus.OK);
  }

  @RequestMapping("/cube/{floorId}")
  public ResponseEntity<Float> getFloorCube(@PathVariable Integer floorId) {

    logger.debug(floorId.toString());
    logger.info("Get floor cube. Floord id: ", floorId.toString());

    Float totalCube = floorService.calculateFloorCube(floorId);
    return new ResponseEntity<>(totalCube, HttpStatus.OK);
  }

  @RequestMapping("/heating/{floorId}")
  public ResponseEntity<Float> getFloorHeating(@PathVariable Integer floorId) {

    logger.debug(floorId.toString());
    logger.info("Get floor heating. Floord id: ", floorId.toString());

    Float totalHeating = floorService.calculateFloorHeating(floorId);
    return new ResponseEntity<>(totalHeating, HttpStatus.OK);
  }
}
