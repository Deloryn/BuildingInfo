package pl.put.poznan.buildingInfo.rest.building;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.put.poznan.buildingInfo.logic.building.BuildingService;


@RestController
@RequestMapping("/building")
public class BuildingController {

  private static final Logger logger = LoggerFactory.getLogger(BuildingController.class);

  @Autowired
  BuildingService buildingService;

  @RequestMapping("/area/{buildingId}")
  public ResponseEntity<Float> getBuildingArea(@PathVariable Integer buildingId) {

    logger.debug(buildingId.toString());
    logger.info("Get building area. Building id: ", buildingId.toString());

    Float totalArea = buildingService.calculateBuildingArea(buildingId);
    return new ResponseEntity<>(totalArea, HttpStatus.OK);
  }

  @RequestMapping("/cube/{buildingId}")
  public ResponseEntity<Float> getBuildingCube(@PathVariable Integer buildingId) {

    logger.debug(buildingId.toString());
    logger.info("Get building cube. Building id: ", buildingId.toString());

    Float totalCube = buildingService.calculateBuildingCube(buildingId);
    return new ResponseEntity<>(totalCube, HttpStatus.OK);
  }

  @RequestMapping("/heating/{buildingId}")
  public ResponseEntity<Float> getBuildingHeating(@PathVariable Integer buildingId) {

    logger.debug(buildingId.toString());
    logger.info("Get building heating. Building id: ", buildingId.toString());

    Float totalHeating = buildingService.calculateBuildingHeating(buildingId);
    return new ResponseEntity<>(totalHeating, HttpStatus.OK);
  }
}


