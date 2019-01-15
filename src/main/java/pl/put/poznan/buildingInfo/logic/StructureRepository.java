package pl.put.poznan.buildingInfo.logic;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.put.poznan.buildingInfo.model.Structure;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * A class that is a broker used in JSON deserialization
 */
@Component
public class StructureRepository {

  /**
   * The filename of the JSON file
   */
  @Value("${datasource}")
  private String fileName;

  /**
   * An instance of the logger. It can be used to display info and debug messages
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(StructureRepository.class);


  /**
   * A method which deserializes JSON and instantiates structure objects
   *
   * @return object of the main structure
   */
  public Structure getStructureInfo() {

    byte[] fileContent = getFileContentFromResource();

    String jsonString = new String(fileContent, StandardCharsets.UTF_8);

    Gson gson = new Gson();
    return gson.fromJson(jsonString, Structure.class);
  }

  /**
   * A method which saves changes to structure objects to file in JSON format
   */
  void saveStructureInfo(Structure structureInfo) {

    Gson gson = new Gson();
    String jsonString = gson.toJson(structureInfo);

    try {
      String path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI()).toString();
      Files.write(Paths.get(path), jsonString.getBytes());
    } catch (IOException | URISyntaxException e) {
      LOGGER.info("Failed to write to file");
      LOGGER.debug(e.getMessage());
    }
  }

  /**
   * A method that reads the JSON file and return its content
   *
   * @return the content of the JSON file
   */
  private byte[] getFileContentFromResource() {

    String path;

    try {
      path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI()).toString();
    } catch (Exception e) {
      return new byte[0];
    }

    byte[] fileContent;

    try {
      fileContent = Files.readAllBytes(Paths.get(path));
    } catch (IOException e) {
      return new byte[0];
    }

    return fileContent;
  }
}
