package com.ArkavQuarium.test;
import com.ArkavQuarium.Food;
import com.ArkavQuarium.Point;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FoodTest {

    @Test
    public void testConstructorFood() {
        Point p = new Point(2.0,3.0);
        Food f = new Food(p);

        double expectedX = 2.0;
        double expectedY = 3.0;

        assertEquals(expectedX,f.getX());
        assertEquals(expectedY,f.getY());

    }
}