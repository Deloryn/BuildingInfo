package pl.put.poznan.buildingInfo.logic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.put.poznan.buildingInfo.logic.repository.StructureCypRepository;
import pl.put.poznan.buildingInfo.model.Structure;

import java.util.List;

@Service
public class StructureCypServiceImpl implements StructureCypService{
    //@Autowired
    //StructureCypRepository structureCypRepository;

    @Override
    public List<Structure> findAll() {
        //return structureCypRepository.findAll();
        return null;
    }
}
