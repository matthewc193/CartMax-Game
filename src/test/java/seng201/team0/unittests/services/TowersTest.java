package seng201.team0.unittests.services;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.game.Player;
import seng201.team0.towers.WoodOne;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(10, testWoodOne.getResourceAmount());
    }

    @Test
    void testUpgradeResourceAmount(){
        Player player = new Player();
        assertEquals(false, testWoodOne.upgradeResourceAmount(player));
        assertEquals(10, testWoodOne.getResourceAmount());
        assertEquals(0,player.getMoney());
        player.increaseMoney(25);
        assertEquals(true, testWoodOne.upgradeResourceAmount(player));
        assertEquals(5, player.getMoney());
        assertEquals(12, testWoodOne.getResourceAmount());
    }

    /**
     * Test upgradeReloadSpeed method, normal Junit test
     */
    @Test
    void testUpgradeReloadSpeed(){
        Player player = new Player();
        assertEquals(false, testWoodOne.upgradeReloadSpeed(player));
        assertEquals(2.0, testWoodOne.getReloadSpeed());
        assertEquals(0,player.getMoney());
        player.increaseMoney(20);
        assertEquals(true, testWoodOne.upgradeReloadSpeed(player));
        assertEquals(0, player.getMoney());
        assertEquals(1.8, testWoodOne.getReloadSpeed());
        player.increaseMoney(1000);
        assertEquals(true, testWoodOne.upgradeReloadSpeed(player));
        assertEquals(true, testWoodOne.upgradeReloadSpeed(player));
        assertEquals(true, testWoodOne.upgradeReloadSpeed(player));
        assertEquals(true, testWoodOne.upgradeReloadSpeed(player));
        assertEquals(1.0, testWoodOne.getReloadSpeed());
        assertEquals(false, testWoodOne.upgradeReloadSpeed(player));
        assertEquals(1.0, testWoodOne.getReloadSpeed());
    }

}
