package seng201.team0.unittests.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seng201.team0.carts.WoodCart;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test carts implementation
 * @author Matthew
 */
public class CartsTest {
    private WoodCart testWoodCart;
    @BeforeEach
    void setUpTest(){
        testWoodCart = new WoodCart();
    }

    /**
     * Testing Constructor method should set to default values
     */
    @Test
    void testConstructor(){
        assertEquals("Wood", testWoodCart.getResourceType());
        assertEquals(30, testWoodCart.getCartCapacity());
        assertEquals(0, testWoodCart.getCurrentResourceAmount());
        assertFalse(testWoodCart.isCartFilled());
    }

    /**
     * Testing increaseResourceAmount method
     */

    @Test
    void testIncreaseResourceAmount(){
        testWoodCart.increaseResourceAmount(10);
        assertEquals(10, testWoodCart.getCurrentResourceAmount());
        testWoodCart.increaseResourceAmount((19));
        assertEquals(29, testWoodCart.getCurrentResourceAmount());
        assertFalse(testWoodCart.isCartFilled());
        testWoodCart.increaseResourceAmount(1);
        assertEquals(30, testWoodCart.getCurrentResourceAmount());
        assertTrue(testWoodCart.isCartFilled());
        testWoodCart.increaseResourceAmount(10);
        assertTrue(testWoodCart.isCartFilled());
    }

}
