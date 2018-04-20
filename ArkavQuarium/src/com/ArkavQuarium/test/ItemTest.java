package com.ArkavQuarium.test;

import static org.junit.jupiter.api.Assertions.*;

import com.ArkavQuarium.Item;
import static com.ArkavQuarium.Constants.*;
import org.junit.jupiter.api.Test;

class ItemTest {
    @Test
    public void testConstructorItemEmpty() {
        Item item = new Item();
        double expectedX = -1.0;
        double expectedY = -1.0;
        assertEquals(expectedX,item.getX());
        assertEquals(expectedY,item.getY());

    }

    @Test
    public void testConstructorItemWithParameters() {
        Item item = new Item(2.0,3.0);
        double expectedX = 2.0;
        double expectedY = 3.0;
        assertEquals(expectedX,item.getX());
        assertEquals(expectedY,item.getY());
    }

    @Test
    public void testMoveItem3Times() {
        Item item = new Item(50.0,50.0);
        double maks = 720;
        for (int x = 0; x<3; x++){
            item.move(maks);
        }
        double expected = Math.min(maks,50.0 + (3*ITEM_SPEED));

        assertEquals(expected,Math.floor(item.getY() * 100) / 100);
    }

    @Test
    public void testMoveItemUntilMax() {
        Item item = new Item(50.0,50.0);
        double maks = 720;
        while(!item.isAtBottom(maks)){
            item.move(maks);
        }
        double expected = maks;
        assertEquals(expected,item.getY());
    }


}