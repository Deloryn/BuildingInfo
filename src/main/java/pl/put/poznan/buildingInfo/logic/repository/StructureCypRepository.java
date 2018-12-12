package pl.put.poznan.buildingInfo.logic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.put.poznan.buildingInfo.model.Structure;

import java.util.List;

@Repository
public interface StructureCypRepository extends JpaRepository<Structure,Long> {
    List<Structure> findAll();
}
