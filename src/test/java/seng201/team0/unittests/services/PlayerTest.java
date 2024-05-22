package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.Player;
import seng201.team0.towers.ClayOne;
import seng201.team0.towers.Tower;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {
    private Player testPlayer;
    @BeforeEach
    public void init() {
        this.testPlayer = new Player();
    }
    @Test
    public void getSelectedTowerTest() {
        Tower tower = new ClayOne();
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
    }
    @Test
    public void addTowerTest() {
        Tower tower = new ClayOne();
        testPlayer.addTower(tower);
        assertEquals(tower, testPlayer.getTowers().get(0));
        assertEquals("selected", testPlayer.getTowers().get(0).getStatus());
    }
    @Test
    public void removeTowerTest() {
        Tower tower = new ClayOne();
        testPlayer.addTower(tower);
        testPlayer.removeTower(tower);
        assertEquals(0, testPlayer.getTowers().size());
    }
    @Test
    public void resetTowersTest() {
        Tower tower = new ClayOne();
        testPlayer.addTower(tower);
        testPlayer.resetTowers();
        assertEquals(0, testPlayer.getTowers().size());
    }
    @Test
    public void buyTowerTest() {
        Tower tower = new ClayOne();
        testPlayer.increaseMoney(tower.getCost());
        boolean canBuy = testPlayer.buyTower(tower);
        assertTrue(canBuy);
        assertEquals(tower, testPlayer.getTowers().get(0));
    }
    @Test
    public void sellTowerTest() {
        Tower tower = new ClayOne();
        testPlayer.addTower(tower);
        testPlayer.sellTower(0);
        assertEquals(tower.getCost(), testPlayer.getMoney());
        assertEquals(0, testPlayer.getTowers().size());
    }
}
