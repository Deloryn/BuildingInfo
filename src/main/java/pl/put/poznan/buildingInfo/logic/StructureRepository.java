package pl.put.poznan.buildingInfo.logic;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.put.poznan.buildingInfo.model.Structure;

import java.io.IOException;
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
  * An instance of the main structure (which is a parent of all other structures)
  */

  /**
   * A method which deserializes JSON and instantiates structure objects
   * @return object of the main structure
   */
  public Structure getStructureInfo() {

    byte[] fileContent = getFileContentFromResource();

    String jsonString = new String(fileContent, StandardCharsets.UTF_8);

    Gson gson = new Gson();
    return gson.fromJson(jsonString, Structure.class);
  }

  /**
   * A method that reads the JSON file and return its content
   * @return the content of the JSON file
   */
  private byte[] getFileContentFromResource() {

    String path = null;

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
