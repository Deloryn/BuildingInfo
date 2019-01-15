package pl.put.poznan.buildingInfo.rest.structure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.buildingInfo.logic.StructureService;
import pl.put.poznan.buildingInfo.model.Structure;

import java.util.ArrayList;

/**
 * A controller class for all structure operations
 */
@RestController
@RequestMapping("/structure")
@CrossOrigin(origins = "http://localhost:3000")
public class StructureController {

  /**
   * An instance of the logger. It can be used to display info and debug messages
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(StructureController.class);

  /**
   * structureService object contains all the logic for structure operations
   */
  @Autowired
  StructureService structureService;

  /**
   * A handler for structure with rooms with higher heating per cube than given
   *
   * @param threshold given heating per cube value
   * @return structures with rooms with heating per cube higher than threshold
   */
  @RequestMapping("/heatingPerCubeGreaterThan/{threshold}")
  public ResponseEntity<ArrayList<Structure>> getStructuresWithHeatingPerCubeExceed(@PathVariable Double threshold) {

    LOGGER.info("Get structures with rooms with heating per cube greater than: " + threshold);

    ArrayList<Structure> structures = structureService.getStructuresWithHeatingPerCubeExceeded(threshold);
    return new ResponseEntity<>(structures, HttpStatus.OK);
  }

  /**
   * A handler for delete structure requests
   *
   * @param structureId id of the structure to delete
   * @return ResponseEntity with 204 code for successful delete and 404 code for not found
   */
  @RequestMapping(value = "/{structureId}", method = RequestMethod.DELETE)
  public ResponseEntity deleteStructure(@PathVariable Integer structureId) {

    LOGGER.debug(structureId.toString());
    LOGGER.info("Delete structure. Structure id: " + structureId.toString());

    Boolean wasSuccessful = structureService.deleteStructure(structureId);
    LOGGER.debug("Delete successful?: " + wasSuccessful);

    if (!wasSuccessful) {
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  /**
   * A handler for structure area requests
   *
   * @param structureId id of the requested structure
   * @return area of the requested structure
   */
  @RequestMapping("/area/{structureId}")
  public ResponseEntity<Double> getStructureArea(@PathVariable Integer structureId) {

    LOGGER.debug(structureId.toString());
    LOGGER.info("Get structure area. Structure id: " + structureId.toString());

    Double totalArea = structureService.getStructureArea(structureId);
    return new ResponseEntity<>(totalArea, HttpStatus.OK);
  }

  /**
   * A handler for structure cube requests
   *
   * @param structureId id of the requested structure
   * @return cube of the requested structure
   */
  @RequestMapping("/cube/{structureId}")
  public ResponseEntity<Double> getStructureCube(@PathVariable Integer structureId) {

    LOGGER.debug(structureId.toString());
    LOGGER.info("Get structure cube. Structure id: " + structureId.toString());

    Double totalCube = structureService.getStructureCube(structureId);
    return new ResponseEntity<>(totalCube, HttpStatus.OK);
  }

  /**
   * A handler for structure light requests
   *
   * @param structureId id of the requested structure
   * @return light of the requested structure
   */
  @RequestMapping("/light/{structureId}")
  public ResponseEntity<Double> getStructureLight(@PathVariable Integer structureId) {

    LOGGER.debug(structureId.toString());
    LOGGER.info("Get structure light. Structure id: " + structureId.toString());

    Double totalLight = structureService.getStructureLight(structureId);
    return new ResponseEntity<>(totalLight, HttpStatus.OK);
  }

  /**
   * A handler for structure heating requests
   *
   * @param structureId id of the requested structure
   * @return heating of the requested structure
   */
  @RequestMapping("/heating/{structureId}")
  public ResponseEntity<Double> getStructureHeating(@PathVariable Integer structureId) {

    LOGGER.debug(structureId.toString());
    LOGGER.info("Get structure heating. Structure id: " + structureId.toString());

    Double totalHeating = structureService.getStructureHeating(structureId);
    return new ResponseEntity<>(totalHeating, HttpStatus.OK);
  }

  /**
   * A handler for structure heating per cube requests
   *
   * @param structureId id of the requested structure
   * @return heating per cube of the requested structure
   */
  @RequestMapping("/heatingPerCube/{structureId}")
  public ResponseEntity<Double> getStructureHeatingPerCube(@PathVariable Integer structureId) {

    LOGGER.debug(structureId.toString());
    LOGGER.info("Get structure heating per cube. Structure id: " + structureId.toString());

    Double totalHeatingPerCube = structureService.getStructureHeatingPerCube(structureId);
    return new ResponseEntity<>(totalHeatingPerCube, HttpStatus.OK);
  }

  /**
   * A handler for structure light per area requests
   *
   * @param structureId id of the requested structure
   * @return light per area of the requested structure
   */
  @RequestMapping("/lightPerArea/{structureId}")
  public ResponseEntity<Double> getStructureLightPerArea(@PathVariable Integer structureId) {

    LOGGER.debug(structureId.toString());
    LOGGER.info("Get structure light per area. Structure id: " + structureId.toString());

    Double totalLightPerArea = structureService.getStructureLightPerArea(structureId);
    return new ResponseEntity<>(totalLightPerArea, HttpStatus.OK);
  }

  /**
   * A handler for structure maintenance cost requests
   *
   * @param structureId id of the requested structure
   * @param unitPrice   price for a single light unit
   * @return maintenance cost for the structure
   */
  @RequestMapping("/maintenance-cost/{structureId}/{unitPrice}")
  public ResponseEntity<Double> getMaintenanceCost(@PathVariable Integer structureId, @PathVariable Double unitPrice) {

    LOGGER.debug(structureId.toString());
    LOGGER.info("Get the maintenance cost for the structure. Structure id: " + structureId.toString() + " cost: " + unitPrice.toString());

    Double maintenanceCost = structureService.getMaintenanceCost(structureId, unitPrice);
    return new ResponseEntity<>(maintenanceCost, HttpStatus.OK);
  }
}


