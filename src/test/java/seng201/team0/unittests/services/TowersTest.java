package seng201.team0.unittests.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.towers.woodOne;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test tower implementation
 * @author Matthew
 */
public class TowersTest {
    private woodOne testWoodOne;
    /**
     * Setup before each test, we create two objects, one an actual
     * instance of our WoodOne class, and another a mocked version
     * that has overridden methods.
     */
    @BeforeEach
    public void setUpTest(){
        testWoodOne = new woodOne();
    }
    @Test
    void testConstructor(){
        assertEquals("Wood", testWoodOne.getResourceType());
        assertEquals(100, testWoodOne.getCost());
        assertEquals(2.0, testWoodOne.getReloadSpeed());
        assertEquals(10, testWoodOne.getResourceAmount());
    }

}
