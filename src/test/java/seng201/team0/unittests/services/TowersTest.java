package seng201.team0.unittests.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.game.Player;
import seng201.team0.towers.WoodOne;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test tower implementation
 * @author Matthew
 */
public class TowersTest {
    private WoodOne testWoodOne;
    /**
     * Setup before each test, we create two objects, one an actual
     * instance of our WoodOne class, and another a mocked version
     * that has overridden methods.
     */
    @BeforeEach
    public void setUpTest(){
        testWoodOne = new WoodOne();
    }
    @Test
    void testConstructor(){
        assertEquals("Wood", testWoodOne.getResourceType());
        assertEquals(100, testWoodOne.getCost());
        assertEquals(2.0, testWoodOne.getReloadSpeed());
        assertEquals(20, testWoodOne.getResourceAmount());
        assertEquals("Wood 1.0", testWoodOne.getTowerName());
    }

    @Test
    void testUpgradeResourceAmount(){
        Player player = new Player();
        assertFalse(testWoodOne.upgradeResourceAmount(player));
        assertEquals(20, testWoodOne.getResourceAmount());
        assertEquals(0,player.getMoney());
        player.increaseMoney(55);
        assertTrue(testWoodOne.upgradeResourceAmount(player));
        assertEquals(5, player.getMoney());
        assertEquals(22, testWoodOne.getResourceAmount());
    }

    /**
     * Test upgradeReloadSpeed method, normal Junit test
     */
    @Test
    void testUpgradeReloadSpeed(){
        Player player = new Player();
        assertFalse(testWoodOne.upgradeReloadSpeed(player));
        assertEquals(2.0, testWoodOne.getReloadSpeed());
        assertEquals(0,player.getMoney());
        player.increaseMoney(50);
        assertTrue(testWoodOne.upgradeReloadSpeed(player));
        assertEquals(0, player.getMoney());
        assertEquals(1.8, testWoodOne.getReloadSpeed());
        player.increaseMoney(1000);
        assertTrue(testWoodOne.upgradeReloadSpeed(player));
        assertTrue(testWoodOne.upgradeReloadSpeed(player));
        assertTrue(testWoodOne.upgradeReloadSpeed(player));
        assertTrue(testWoodOne.upgradeReloadSpeed(player));
        assertTrue(testWoodOne.upgradeReloadSpeed(player));
        assertTrue(testWoodOne.upgradeReloadSpeed(player));
        assertTrue(testWoodOne.upgradeReloadSpeed(player));
        assertTrue(testWoodOne.upgradeReloadSpeed(player));
        assertEquals(0.2, testWoodOne.getReloadSpeed());
        assertFalse(testWoodOne.upgradeReloadSpeed(player));
        assertEquals(0.2, testWoodOne.getReloadSpeed());
    }

}
