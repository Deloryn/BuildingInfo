package pl.put.poznan.buildingInfo.logic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.put.poznan.buildingInfo.model.Structure;

import javax.annotation.Resource;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

/**
 * A class containing test methods for StructureService class
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StructureServiceTest {

  /**
   * An instance of the mocked structure repository (to inject into structure service)
   */
  @Mock
  private StructureRepository structureRepository;

  /**
   * An instance of the tested class
   */
  @Autowired
  @InjectMocks
  @Resource
  StructureService structureService;

  /**
   * A private method to generate a test case
   *
   * @return it returns the main structure (the owner of all the children)
   */
  private Structure generateTestMainStructure() {
    Structure structure3 = new Structure(3, "L053 BT", null, 123.34, 1002.43, 234.43, 467.54);
    Structure structure4 = new Structure(4, "CW 1", null, 83.34, 702.43, 134.43, 167.54);

    Structure structure6 = new Structure(6, "CW 8", null, 83.34, 772.43, 145.43, 154.54);
    Structure structure7 = new Structure(7, "CW 9", null, 83.34, 752.43, 187.43, 123.54);

    ArrayList<Structure> structuresFor2 = new ArrayList<>();
    structuresFor2.add(structure3);
    structuresFor2.add(structure4);

    ArrayList<Structure> structuresFor5 = new ArrayList<>();
    structuresFor5.add(structure6);
    structuresFor5.add(structure7);

    Structure structure2 = new Structure(2, null, structuresFor2, null, null, null, null);
    Structure structure5 = new Structure(5, null, structuresFor5, null, null, null, null);

    ArrayList<Structure> structuresFor1 = new ArrayList<>();
    structuresFor1.add(structure2);
    structuresFor1.add(structure5);

    Structure structure1 = new Structure(1, "Centrum Wykładowe", structuresFor1, null, null, null, null);

    ArrayList<Structure> structuresForMain = new ArrayList<>();
    structuresForMain.add(structure1);

    return new Structure(null, null, structuresForMain, null, null, null, null);
  }

  @Before
  public void setUp() {
    Structure mainStructure = generateTestMainStructure();
    MockitoAnnotations.initMocks(this);
    when(structureRepository.getStructureInfo()).thenReturn(mainStructure);
  }

  /**
   * Test case for findStructure. It should return non-null Structure object
   */
  @Test
  public void findStructureForExistingStructuresTest() {
    Assert.assertNotNull(structureService.findStructure(1));
    Assert.assertNotNull(structureService.findStructure(6));
    Assert.assertNotNull(structureService.findStructure(5));
    verify(structureRepository, times(3)).getStructureInfo();
  }

  /**
   * Test case for findStructure. It should return null
   */
  @Test
  public void findStructureForNotExistingStructuresTest() {
    Assert.assertNull(structureService.findStructure(27));
    Assert.assertNull(structureService.findStructure(29));
    Assert.assertNull(structureService.findStructure(-5));
    Assert.assertNull(structureService.findStructure(-320));
    verify(structureRepository, times(4)).getStructureInfo();
  }

  /**
   * Test method that checks if areas of structures are calculated properly
   */
  @Test
  public void getStructureAreaForExistingStructuresTest() {
    Assert.assertEquals((Double) 123.34, structureService.getStructureArea(3));
    Assert.assertEquals((Double) 166.68, structureService.getStructureArea(5));
    Assert.assertEquals((Double) 373.36, structureService.getStructureArea(1));
    Assert.assertEquals((Double) 83.34, structureService.getStructureArea(7));
    verify(structureRepository, times(4)).getStructureInfo();
  }

  /**
   * Test method that checks if areas of non-existing structures are equal to 0
   */
  @Test
  public void getStructureAreaForNotExistingStuctureTest() {
    Assert.assertEquals((Double) 0.0, structureService.getStructureArea(-2));
    Assert.assertEquals((Double) 0.0, structureService.getStructureArea(9));
    verify(structureRepository, times(2)).getStructureInfo();
  }

  /**
   * Test method that checks if cubes of structures are calculated properly
   */
  @Test
  public void getStructureCubeForExistingStructuresTest() {
    Assert.assertEquals((Double) 772.43, structureService.getStructureCube(6));
    Assert.assertEquals((Double) 0.0, structureService.getStructureCube(-27));
    Assert.assertEquals((Double) 1524.86, structureService.getStructureCube(5));
    Assert.assertEquals((Double) 752.43, structureService.getStructureCube(7));
    verify(structureRepository, times(4)).getStructureInfo();
  }

  /**
   * Test method that checks if cubes of non-existing structures are equal to 0
   */
  @Test
  public void getStructureCubeForNotExistingStructuresTest() {
    Assert.assertEquals((Double) 0.0, structureService.getStructureCube(-27));
    Assert.assertEquals((Double) 0.0, structureService.getStructureCube(0));
    Assert.assertEquals((Double) 0.0, structureService.getStructureCube(10));
    verify(structureRepository, times(3)).getStructureInfo();
  }

  /**
   * Test method that checks if light of structures is calculated properly
   */
  @Test
  public void getStructureLightForExistingStructuresTest() {
    Assert.assertEquals((Double) 123.54, structureService.getStructureLight(7));
    Assert.assertEquals((Double) 913.16, structureService.getStructureLight(1), 0.0005);
    Assert.assertEquals((Double) 278.08, structureService.getStructureLight(5));
    Assert.assertEquals((Double) 167.54, structureService.getStructureLight(4));
    verify(structureRepository, times(4)).getStructureInfo();
  }

  /**
   * Test method that checks if lights of non-existing structures are equal to 0
   */
  @Test
  public void getStructureLightForNotExistingStructuresTest() {
    Assert.assertEquals((Double) 0.0, structureService.getStructureLight(-12));
    Assert.assertEquals((Double) 0.0, structureService.getStructureLight(0));
    Assert.assertEquals((Double) 0.0, structureService.getStructureLight(13));
    verify(structureRepository, times(3)).getStructureInfo();
  }

  /**
   * Test method that checks if heating of structures is calculated properly
   */
  @Test
  public void getStructureHeatingForExistingStructuresTest() {
    Assert.assertEquals((Double) 187.43, structureService.getStructureHeating(7));
    Assert.assertEquals((Double) 701.72, structureService.getStructureHeating(1));
    Assert.assertEquals((Double) 145.43, structureService.getStructureHeating(6));
    Assert.assertEquals((Double) 332.86, structureService.getStructureHeating(5));
    verify(structureRepository, times(4)).getStructureInfo();
  }

  /**
   * Test method that checks if heatings of non-existing structures are equal to 0
   */
  @Test
  public void getStructureHeatingForNotExistingStructuresTest() {
    Assert.assertEquals((Double) 0.0, structureService.getStructureHeating(-4));
    Assert.assertEquals((Double) 0.0, structureService.getStructureHeating(0));
    Assert.assertEquals((Double) 0.0, structureService.getStructureHeating(31));
    verify(structureRepository, times(3)).getStructureInfo();
  }

  /**
   * Test method that checks if heating per cube of structures is calculated properly
   */
  @Test
  public void getStructureHeatingPerCubeForExistingStructuresTest() {
    Assert.assertEquals(0.2172696, structureService.getStructureHeatingPerCube(1), 0.005);
    Assert.assertEquals(0.2182888, structureService.getStructureHeatingPerCube(5), 0.005);
    Assert.assertEquals(0.2490995, structureService.getStructureHeatingPerCube(7), 0.005);
    verify(structureRepository, times(3)).getStructureInfo();
  }

  /**
   * Test method that checks if heatings per cube of non-existing structures are equal to 0
   */
  @Test
  public void getStructureHeatingPerCubeForNotExistingStructuresTest() {
    Assert.assertEquals((Double) 0.0, structureService.getStructureHeatingPerCube(-1));
    Assert.assertEquals(0.0, structureService.getStructureHeatingPerCube(0), 0.005);
    verify(structureRepository, times(2)).getStructureInfo();
  }

  /**
   * Test method that checks if light per area of structures is calculated properly
   */
  @Test
  public void getStructureLightPerAreaForExistingStructuresTest() {
    Assert.assertEquals(2.445789586, structureService.getStructureLightPerArea(1), 0.005);
    Assert.assertEquals(1.668346532, structureService.getStructureLightPerArea(5), 0.005);
    Assert.assertEquals((Double) 3.790659964, structureService.getStructureLightPerArea(3), 0.005);
    verify(structureRepository, times(3)).getStructureInfo();
  }

  /**
   * Test method that checks if lights per area of non-existing structures are equal to 0
   */
  @Test
  public void getStructureLightPerAreaForNotExistingStructuresTest() {
    Assert.assertEquals((Double) 0.0, structureService.getStructureLightPerArea(-4));
    Assert.assertEquals(0.0, structureService.getStructureLightPerArea(0), 0.005);
    Assert.assertEquals((Double) 0.0, structureService.getStructureLightPerArea(8));
    verify(structureRepository, times(3)).getStructureInfo();
  }

  /**
   * Test method that checks if the maintenance cost of structures is calculated properly
   */
  @Test
  public void getMaintenanceCostForExistingStructuresTest() {
    Assert.assertEquals(0.0, structureService.getMaintenanceCost(5, 0.0), 0.005);
    Assert.assertEquals(220.9922, structureService.getMaintenanceCost(6, 1.43), 0.005);
    Assert.assertEquals(750.816, structureService.getMaintenanceCost(5, 2.70), 0.005);
    verify(structureRepository, times(3)).getStructureInfo();
  }

  /**
   * Test method that checks if the maintenance costs of non-existing structures are equal to 0
   */
  @Test
  public void getMaintenanceCostForNotExistingStructuresTest() {
    Assert.assertEquals(0.0, structureService.getMaintenanceCost(-3, 1.20), 0.005);
    Assert.assertEquals(0.0, structureService.getMaintenanceCost(0, 1.50), 0.005);
    verify(structureRepository, times(2)).getStructureInfo();
  }

  /**
   * Test method that checks when the price is null if the maintenance costs of non-existing structures are equal to 0
   */
  @Test
  public void getMaintenanceCostForNotExistingStructuresWithNullPriceTest() {
    Assert.assertEquals(0.0, structureService.getMaintenanceCost(-3, null), 0.005);
    Assert.assertEquals(0.0, structureService.getMaintenanceCost(0, null), 0.005);
    verify(structureRepository, times(2)).getStructureInfo();
  }

  /**
   * Test method that checks when the price null if the maintenance cost of existing structures are equal to 0
   */
  @Test
  public void getMaintenanceCostForExistingStructuresWithNullPriceTest() {
    Assert.assertEquals((Double) 0.0, structureService.getMaintenanceCost(5, null));
    Assert.assertEquals((Double) 0.0, structureService.getMaintenanceCost(6, null));
    Assert.assertEquals((Double) 0.0, structureService.getMaintenanceCost(5, null));
    Assert.assertEquals((Double) 0.0, structureService.getMaintenanceCost(1, null));
    verify(structureRepository, times(4)).getStructureInfo();
  }

  /**
   * Test case for deleteStructure. It should return false on failure
   */
  @Test
  public void deleteStructureForNotExistingStructuresTest() {
    Structure structureInfoBefore = structureRepository.getStructureInfo();
    Boolean wasSuccessful1 = structureService.deleteStructure(27);
    Boolean wasSuccessful2 = structureService.deleteStructure(-5);
    Structure structureInfoAfter = structureRepository.getStructureInfo();

    Assert.assertFalse(wasSuccessful1);
    Assert.assertFalse(wasSuccessful2);
    Assert.assertEquals(structureInfoBefore, structureInfoAfter);
  }

  /**
   * Test case for deleteStructure. It should return true on success
   */
  @Test
  public void deleteStructureForExistingStructuresTest() {
    Boolean wasSuccessful = structureService.deleteStructure(1);
    verify(structureRepository, times(1)).getStructureInfo();
    verify(structureRepository, times(1)).saveStructureInfo(any(Structure.class));

    Structure structureInfoAfter = structureRepository.getStructureInfo();
    Assert.assertEquals(0, structureInfoAfter.getStructures().size());
    Assert.assertTrue(wasSuccessful);
  }
}