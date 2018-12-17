package pl.put.poznan.buildingInfo.logic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * A class containing test methods for StructureService class
 */
public class StructureServiceTest {

    /**
     * An instance of the tested class
     */
    private StructureService structureService;

    /**
     * A method that is executed before each test method.
     * It prepares Structure Service and StructureRepository with test data (from test_structures.json)
     */
    @Before
    public void setUp() {
        StructureRepository structureRepository = new StructureRepository();
        structureRepository.getStructureInfo("test_structures.json");
        this.structureService = new StructureService();
        this.structureService.setStructureRepository(structureRepository);
    }

    /**
     * Test method for findStructure. It should return Structure object or null if it does not exist
     */
    @Test
    public void findStructureTest() {
        Assert.assertNull(structureService.findStructure(27));
        Assert.assertNull(structureService.findStructure(29));
        Assert.assertNull(structureService.findStructure(-5));
        Assert.assertNull(structureService.findStructure(-320));
        Assert.assertNotNull(structureService.findStructure(0));
        Assert.assertNotNull(structureService.findStructure(6));
        Assert.assertNotNull(structureService.findStructure(5));
    }

    /**
     * Test method that checks if areas of structures are calculated properly
     */
    @Test
    public void getStructureAreaTest() {
        Assert.assertEquals((Double) 11280.0, structureService.getStructureArea(0));
        Assert.assertEquals((Double) 0.0, structureService.getStructureArea(-2));
        Assert.assertEquals((Double) 11164.0, structureService.getStructureArea(5));
        Assert.assertEquals((Double) 116.0, structureService.getStructureArea(1));
        Assert.assertEquals((Double) 1240.0, structureService.getStructureArea(7));
    }

    /**
     * Test method that checks if cubes of structures are calculated properly
     */
    @Test
    public void getStructureCubeTest() {
        Assert.assertEquals((Double) 22322.0, structureService.getStructureCube(0));
        Assert.assertEquals((Double) 0.0, structureService.getStructureCube(-27));
        Assert.assertEquals((Double) 22198.0, structureService.getStructureCube(5));
        Assert.assertEquals((Double) 20235.0, structureService.getStructureCube(6));
    }

    /**
     * Test method that checks if light of structures is calculated properly
     */
    @Test
    public void getStructureLightTest() {
        Assert.assertEquals((Double) 365.0, structureService.getStructureLight(1));
        Assert.assertEquals((Double) 12416.0, structureService.getStructureLight(0));
        Assert.assertEquals((Double) 12051.0, structureService.getStructureLight(5));
        Assert.assertEquals((Double) 0.0, structureService.getStructureLight(-12));
        Assert.assertEquals((Double) 290.0, structureService.getStructureLight(4));
    }

    /**
     * Test method that checks if heating of structures is calculated properly
     */
    @Test
    public void getStructureHeatingTest() {
        Assert.assertEquals((Double) 24189.0, structureService.getStructureHeating(0));
        Assert.assertEquals((Double) 7296.0, structureService.getStructureHeating(1));
        Assert.assertEquals((Double) 0.0, structureService.getStructureHeating(-4));
        Assert.assertEquals((Double) 1250.0, structureService.getStructureHeating(6));
        Assert.assertEquals((Double) 16893.0, structureService.getStructureHeating(5));
    }

    /**
     * Test method that checks if heating per cube of structures is calculated properly
     */
    @Test
    public void getStructureHeatingPerCubeTest() {
        Assert.assertEquals((Double) 0.0, structureService.getStructureHeatingPerCube(-1));
        Assert.assertEquals(1.083639459, structureService.getStructureHeatingPerCube(0), 0.005);
        Assert.assertEquals(58.838709677, structureService.getStructureHeatingPerCube(1), 0.005);
        Assert.assertEquals(0.761014506, structureService.getStructureHeatingPerCube(5), 0.005);
        Assert.assertEquals(10.929577465, structureService.getStructureHeatingPerCube(7), 0.005);
    }

    /**
     * Test method that checks if light per area of structures is calculated properly
     */
    @Test
    public void getStructureLightPerAreaTest() {
        Assert.assertEquals((Double) 0.0, structureService.getStructureLightPerArea(-4));
        Assert.assertEquals(1.10070922, structureService.getStructureLightPerArea(0), 0.005);
        Assert.assertEquals(3.146551724, structureService.getStructureLightPerArea(1), 0.005);
        Assert.assertEquals(1.079451809, structureService.getStructureLightPerArea(5), 0.005);
        Assert.assertEquals((Double) 3.0, structureService.getStructureLightPerArea(3));
        Assert.assertEquals((Double) 0.0, structureService.getStructureLightPerArea(8));
    }

    /**
     * Test method that checks if the maintenance cost of structures is calculated properly
     */
    @Test
    public void getMaintenanceCostTest() {
        Assert.assertEquals(0.0, structureService.getMaintenanceCost(-3, 1.20), 0.005);
        Assert.assertEquals(0.0, structureService.getMaintenanceCost(5, 0.0), 0.005);
        Assert.assertEquals(4320.03, structureService.getMaintenanceCost(6, 1.43), 0.005);
        Assert.assertEquals(32537.7, structureService.getMaintenanceCost(5, 2.70), 0.005);
        Assert.assertEquals(985.5, structureService.getMaintenanceCost(1, 2.70), 0.008);
        Assert.assertEquals(18624.0, structureService.getMaintenanceCost(0, 1.50), 0.005);
    }
}