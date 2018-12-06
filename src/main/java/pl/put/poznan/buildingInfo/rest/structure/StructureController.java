package pl.put.poznan.buildingInfo.rest.structure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.put.poznan.buildingInfo.logic.StructureService;

@RestController
@RequestMapping("/structure")
public class StructureController {

  private static final Logger logger = LoggerFactory.getLogger(StructureController.class);

  @Autowired
  StructureService structureService;

  @RequestMapping("/area/{structureId}")
  public ResponseEntity<Double> getStructureArea(@PathVariable Integer structureId) {

    logger.debug(structureId.toString());
    logger.info("Get structure area. Structure id: " + structureId.toString());

    Double totalArea = structureService.getStructureArea(structureId);
    return new ResponseEntity<>(totalArea, HttpStatus.OK);
  }

  @RequestMapping("/cube/{structureId}")
  public ResponseEntity<Double> getStructureCube(@PathVariable Integer structureId) {

    logger.debug(structureId.toString());
    logger.info("Get structure cube. Structure id: " + structureId.toString());

    Double totalCube = structureService.getStructureCube(structureId);
    return new ResponseEntity<>(totalCube, HttpStatus.OK);
  }

  @RequestMapping("/light/{structureId}")
  public ResponseEntity<Double> getStructureLight(@PathVariable Integer structureId) {

    logger.debug(structureId.toString());
    logger.info("Get structure light. Structure id: " + structureId.toString());

    Double totalLight = structureService.getStructureLight(structureId);
    return new ResponseEntity<>(totalLight, HttpStatus.OK);
  }

  @RequestMapping("/heating/{structureId}")
  public ResponseEntity<Double> getStructureHeating(@PathVariable Integer structureId) {

    logger.debug(structureId.toString());
    logger.info("Get structure heating. Structure id: " + structureId.toString());

    Double totalHeating = structureService.getStructureHeating(structureId);
    return new ResponseEntity<>(totalHeating, HttpStatus.OK);
  }

  @RequestMapping("/heating-per-cube/{structureId}")
  public ResponseEntity<Double> getStructureHeatingPerCube(@PathVariable Integer structureId) {

    logger.debug(structureId.toString());
    logger.info("Get structure heating per cube. Structure id: " + structureId.toString());

    Double totalHeatingPerCube = structureService.getStructureHeatingPerCube(structureId);
    return new ResponseEntity<>(totalHeatingPerCube, HttpStatus.OK);
  }

  @RequestMapping("/light-per-area/{structureId}")
  public ResponseEntity<Double> getStructureLightPerArea(@PathVariable Integer structureId) {

    logger.debug(structureId.toString());
    logger.info("Get structure light per area. Structure id: " + structureId.toString());

    Double totalLightPerArea = structureService.getStructureLightPerArea(structureId);
    return new ResponseEntity<>(totalLightPerArea, HttpStatus.OK);
  }
}


