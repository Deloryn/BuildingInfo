package pl.put.poznan.buildingInfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Main class of the project
 */
@SpringBootApplication(scanBasePackages = {"pl.put.poznan.buildingInfo"})
public class BuildingInfoApplication {

  /**
   * Main method of the project. Here all the application working starts from here
   *
   * @param args arguments given to the app from a console
   */
  public static void main(String[] args) {
    SpringApplication.run(BuildingInfoApplication.class, args);
  }
}
