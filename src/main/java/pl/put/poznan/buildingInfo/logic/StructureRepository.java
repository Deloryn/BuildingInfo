package pl.put.poznan.buildingInfo.logic;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import pl.put.poznan.buildingInfo.model.Structure;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class StructureRepository {

  private static final String FILE_NAME = "structures.json";

  public Structure getStructureInfo() {

    byte[] fileContent = getFileContentFromResource();

    String jsonString = new String(fileContent, StandardCharsets.UTF_8);

    Gson gson = new Gson();
    return gson.fromJson(jsonString, Structure.class);
  }

  private byte[] getFileContentFromResource() {

    String path = null;

    try {
      path = Paths.get(getClass().getClassLoader().getResource(FILE_NAME).toURI()).toString();
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
