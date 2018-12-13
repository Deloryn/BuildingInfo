package pl.put.poznan.buildingInfo.logic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.put.poznan.buildingInfo.model.Structure;

/**
 * A class containing test methods for StructureRepository class
 */
public class StructureRepositoryTest {

    /**
     * An instance of the tested class
     */
    private StructureRepository structureRepository;

    /**
     * A method that is executed before each test method.
     * It prepares fresh instance of Structure Repository
     */
    @Before
    public void setUp() {
        this.structureRepository = new StructureRepository();
    }

    /**
     * Test method for getStructureInfo(). It checks if deserialized JSON is the same as the hard-coded one
     */
    @Test
    public void getStructureInfoTest() {
        Structure structure3 = new Structure(3, "L053 BT", null, 123.34, 1002.43, 234.43, 467.54);
        Structure structure4 = new Structure(4, "CW 1", null, 83.34, 702.43, 134.43, 167.54);
        Structure structure6 = new Structure(6, "CW 8", null, 83.34, 772.43, 145.43, 154.54);
        Structure structure7 = new Structure(7, "CW 9", null, 83.34, 752.43, 187.43, 123.54);

        Structure[] structuresFor2 = {structure3, structure4};
        Structure structure2 = new Structure(2, null, structuresFor2, null, null, null, null);

        Structure[] structuresFor5 = {structure6, structure7};
        Structure structure5 = new Structure(5, null, structuresFor5, null, null, null, null);

        Structure[] structuresFor1 = {structure2, structure5};
        Structure structure1 = new Structure(1, "Centrum Wykładowe", structuresFor1, null, null, null, null);

        Structure[] structuresForMockMain = {structure1};
        Structure mockMainStructure = new Structure(null, null, structuresForMockMain, null, null, null, null);

        Structure mainStructure = this.structureRepository.getStructureInfo();
        Assert.assertEquals(mockMainStructure, mainStructure);
    }

    /**
     * Test method for getStructureInfo(String fileName). It checks if deserialized JSON is the same as the hard-coded one
     */
    @Test
    public void getStructureInfoWithParameterTest() {
        Structure structure2 = new Structure(2, "Chicken Room 1", null, 17.0, 90.0, 7120.0, 45.0);
        Structure structure3 = new Structure(3, "Chicken Room 2", null, 10.0, 20.0, 120.0, 30.0);
        Structure structure4 = new Structure(4, "Chicken Room 3", null, 89.0, 14.0, 56.0, 290.0);
        Structure structure6 = new Structure(6, "Chicken Room 4", null, 9847.0, 20235.0, 1250.0, 3021.0);
        Structure structure7 = new Structure(7, "Chicken Room 5", null, 1240.0, 1420.0, 15520.0, 9030.0);
        Structure structure8 = new Structure(8, "Chicken Room 6", null, 77.0, 543.0, 123.0, 0.0);

        Structure[] structuresFor1 = {structure2, structure3, structure4};
        Structure structure1 = new Structure(1, "Chicken floor 1", structuresFor1, null, null, null, null);

        Structure[] structuresFor5 = {structure6, structure7, structure8};
        Structure structure5 = new Structure(5, "Chicken floor 2", structuresFor5, null, null, null, null);

        Structure[] structuresForMockMain = {structure1, structure5};
        Structure mockMainStructure = new Structure(0, "Chicken farm", structuresForMockMain, null, null, null, null);

        Structure mainStructure = this.structureRepository.getStructureInfo("test_structures.json");
        Assert.assertEquals(mockMainStructure, mainStructure);
    }
}