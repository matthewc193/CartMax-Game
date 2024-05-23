package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.game.Player;
import seng201.team0.towers.*;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player testPlayer;
    @BeforeEach
    public void init() {
        this.testPlayer = new Player();
    }
    @Test
    public void constructorTest() {
        assertNull(testPlayer.getName());
        assertEquals(0, testPlayer.getTowers().size());
        assertEquals(0, testPlayer.getMoney());
    }
    @Test
    public void getSelectedTowerTest() {
        Tower tower = new ClayOne();
        assertTrue(testPlayer.getSelectedTowers().isEmpty());
        testPlayer.addTower(tower);
        assertEquals(1, testPlayer.getSelectedTowers().size());
    }
    @Test
    public void increaseMoneyTest() {
        testPlayer.increaseMoney(100);
        assertEquals(100, testPlayer.getMoney());
    }
    @Test
    public void decreaseMoneyTest() {
        testPlayer.increaseMoney(100);
        testPlayer.decreaseMoney(50);
        assertEquals(50, testPlayer.getMoney());
        testPlayer.decreaseMoney(100); // Negative balance
        assertEquals(-50, testPlayer.getMoney());
    }
    @Test
    public void addTowerTest() {
        Tower tower = new ClayTwo();
        testPlayer.addTower(tower);
        assertEquals(tower, testPlayer.getTowers().get(0));
        assertEquals("selected", testPlayer.getTowers().get(0).getStatus());
        for (int i = 0; i < 5; i++) { // Adding more than five towers
            testPlayer.addTower(tower);
            assertEquals("selected", tower.getStatus());
        }
        testPlayer.addTower(tower);
        assertEquals("reserved", tower.getStatus());
        assertEquals(6, testPlayer.getTowers().size());
    }
    @Test
    public void removeTowerTest() {
        Tower tower = new StoneOne();
        testPlayer.addTower(tower);
        testPlayer.removeTower(tower);
        assertEquals(0, testPlayer.getTowers().size());
    }
    @Test
    public void resetTowersTest() {
        Tower tower = new StoneTwo();
        testPlayer.addTower(tower);
        assertEquals(1, testPlayer.getTowers().size());
        testPlayer.resetTowers();
        assertEquals(0, testPlayer.getTowers().size());
    }
    @Test
    public void buyTowerTest() {
        Tower tower = new WoodOne();
        testPlayer.increaseMoney(tower.getCost());
        boolean canBuy = testPlayer.buyTower(tower);
        assertTrue(canBuy);
        assertEquals(tower, testPlayer.getTowers().get(0));
        boolean canBuyTwo = testPlayer.buyTower(tower); // Not enough money
        assertFalse(canBuyTwo);
        assertEquals(1, testPlayer.getTowers().size());
    }
    @Test
    public void sellTowerTest() {
        Tower tower = new WoodTwo();
        testPlayer.addTower(tower);
        testPlayer.sellTower(0);
        assertEquals(tower.getCost(), testPlayer.getMoney());
        assertEquals(0, testPlayer.getTowers().size());
        testPlayer.sellTower(1);
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> { // Index out of bounds
            testPlayer.sellTower(0);
        });
        assertEquals("Tower index out of bounds", exception.getMessage());
    }
}
