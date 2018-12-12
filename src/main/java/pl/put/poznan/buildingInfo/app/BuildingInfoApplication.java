package pl.put.poznan.buildingInfo.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.put.poznan.buildingInfo.model.Structure;

import java.util.ArrayList;
import java.util.List;


/**
 * Main class of the project
 */
@SpringBootApplication(scanBasePackages = {"pl.put.poznan.buildingInfo"})
//@ComponentScan({"com.delivery.request"})
@EntityScan("pl.put.poznan.buildingInfo.model")
//@EnableJpaRepositories("pl.put.poznan.buildingInfo.logic.repository")
public class BuildingInfoApplication {

    List<Structure> structureList = new ArrayList<Structure>();
    /**
     * Main method of the project. Here all the application working starts from here
     * @param args arguments given to the app from a console
     */
    public static void main(String[] args) {
        SpringApplication.run(BuildingInfoApplication.class, args);
        System.out.println("over");
    }
}
