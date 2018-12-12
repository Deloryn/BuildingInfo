package pl.put.poznan.buildingInfo.rest.structure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.put.poznan.buildingInfo.logic.StructureService;
import pl.put.poznan.buildingInfo.model.Structure;

import java.io.File;
import java.io.IOException;

/**
 * A controller class for all structure operations
 */
@RestController
@RequestMapping("/structure")
public class StructureController {

  /**
   * An instance of the logger. It can be used to display info and debug messages
   */
  private static final Logger logger = LoggerFactory.getLogger(StructureController.class);

  /**
   * structureService object contains all the logic for structure operations
   */
  @Autowired
  StructureService structureService = new StructureService();

  /**
   * A handler for structure area requests
   * @param structureId id of the requested structure
   * @return area of the requested structure
   */
  @RequestMapping("/area/{structureId}")
  public ResponseEntity<Double> getStructureArea(@PathVariable Integer structureId) {
    System.out.println("totalArea ");
    logger.debug(structureId.toString());
    logger.info("Get structure area. Structure id: " + structureId.toString());

    Double totalArea = structureService.getStructureArea(structureId);
    System.out.println("totalArea " + totalArea);
    return new ResponseEntity<>(totalArea, HttpStatus.OK);
  }

  /**
   * A handler for structure cube requests
   * @param structureId id of the requested structure
   * @return cube of the requested structure
   */
  @RequestMapping("/cube/{structureId}")
  public ResponseEntity<Double> getStructureCube(@PathVariable Integer structureId) {

    logger.debug(structureId.toString());
    logger.info("Get structure cube. Structure id: " + structureId.toString());

    Double totalCube = structureService.getStructureCube(structureId);
    return new ResponseEntity<>(totalCube, HttpStatus.OK);
  }

  /**
   * A handler for structure light requests
   * @param structureId id of the requested structure
   * @return light of the requested structure
   */
  @RequestMapping("/light/{structureId}")
  public ResponseEntity<Double> getStructureLight(@PathVariable Integer structureId) {

    logger.debug(structureId.toString());
    logger.info("Get structure light. Structure id: " + structureId.toString());

    Double totalLight = structureService.getStructureLight(structureId);
    return new ResponseEntity<>(totalLight, HttpStatus.OK);
  }


  /**
   * A handler for structure heating requests
   * @param structureId id of the requested structure
   * @return heating of the requested structure
   */
  @RequestMapping("/heating/{structureId}")
  public ResponseEntity<Double> getStructureHeating(@PathVariable Integer structureId) {

    logger.debug(structureId.toString());
    logger.info("Get structure heating. Structure id: " + structureId.toString());

    Double totalHeating = structureService.getStructureHeating(structureId);
    return new ResponseEntity<>(totalHeating, HttpStatus.OK);
  }

  /**
   * A handler for structure heating per cube requests
   * @param structureId id of the requested structure
   * @return heating per cube of the requested structure
   */
  @RequestMapping("/heating-per-cube/{structureId}")
  public ResponseEntity<Double> getStructureHeatingPerCube(@PathVariable Integer structureId) {

    logger.debug(structureId.toString());
    logger.info("Get structure heating per cube. Structure id: " + structureId.toString());

    Double totalHeatingPerCube = structureService.getStructureHeatingPerCube(structureId);
    return new ResponseEntity<>(totalHeatingPerCube, HttpStatus.OK);
  }

  /**
   * A handler for structure light per area requests
   * @param structureId id of the requested structure
   * @return light per area of the requested structure
   */
  @RequestMapping("/light-per-area/{structureId}")
  public ResponseEntity<Double> getStructureLightPerArea(@PathVariable Integer structureId) {

    logger.debug(structureId.toString());
    logger.info("Get structure light per area. Structure id: " + structureId.toString());

    Double totalLightPerArea = structureService.getStructureLightPerArea(structureId);
    return new ResponseEntity<>(totalLightPerArea, HttpStatus.OK);
  }

  /**
   * A handler for structure maintenance cost requests
   * @param structureId id of the requested structure
   * @param unitPrice price for a single light unit
   * @return maintenance cost for the structure
   */
  @RequestMapping("/maintenance-cost/{structureId}/{unitPrice}")
  public ResponseEntity<Double> getMaintenanceCost(@PathVariable Integer structureId, @PathVariable Double unitPrice) {

    logger.debug(structureId.toString());
    logger.info("Get the maintenance cost for the structure. Structure id: " + structureId.toString() + " cost: " + unitPrice.toString());

    Double maintenanceCost = structureService.getMaintenanceCost(structureId, unitPrice);
    return new ResponseEntity<>(maintenanceCost, HttpStatus.OK);
  }

  @RequestMapping("/create/{structureId}/{name}/{area}/{cube}/{heating}/{light}")
  public void createStructureArea(@PathVariable Integer structureId, @PathVariable String name, @PathVariable Double area,
                                  @PathVariable Double cube, @PathVariable Double heating, @PathVariable Double light) throws IOException {
    System.out.println("poszlo " + structureId);
    System.out.println("typ " + structureId.getClass().getName());
    structureService.findStructure(1);
    System.out.println("sama metoda");
    //Structure structure = new Structure(structureId,name,area,cube,heating,light);
    Structure structure = structureService.findStructure(1);
    System.out.println("zfindowalo");
    try{
      System.out.println("tkie m id: " + structure.getName());
    }
    catch (NullPointerException e){
      System.out.println("Caught the NullPointerException");
    }
    ObjectMapper mapper = new ObjectMapper();
    Structure obj = mapper.readValue(new File("d:\\javy\\bildowanie\\src\\main\\resources\\structures.json"), Structure.class);
    System.out.println("poszlo " + obj.getId());
    if(obj == null) System.out.println("sdvsdv");
    try{
      System.out.println("tkie m id: " + structureService.findStructure(structureId).getName());
    }
    catch (NullPointerException e){
      System.out.println("Caught the NullPointerException");
    }

  }
}


