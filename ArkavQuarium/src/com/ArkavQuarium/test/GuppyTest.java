package com.ArkavQuarium.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import com.ArkavQuarium.Point;
import com.ArkavQuarium.Guppy;
import com.ArkavQuarium.Food;
import com.ArkavQuarium.LinkedList;

class GuppyTest {
    @Test
    public void testConstructorGuppy() {
        Guppy guppy = new Guppy(20000, 30.0, 40.0);

        double x = 30.0;
        double y = 40.0;
        assertEquals(x, guppy.getX());
        assertEquals(y, guppy.getY());
    }

    @Test
    public void testMoveToEat() {
        long time = 0;
        Guppy guppy = new Guppy(time, 30.0, 40.0);
        Food food = new Food(new Point(20.0, 30.0));
        LinkedList<Food> listOfFood = new LinkedList<>();
        listOfFood.add(food);

        while (!listOfFood.isEmpty()) {
            guppy.moveToEat(listOfFood, food, time);
            time++;
        }

        assertEquals(true, listOfFood.isEmpty());
        assertEquals(20.0, guppy.getX());
        assertEquals(30.0, guppy.getY());
    }

    @Test
    public void testMoveRandom() {
        long time = 0;
        Guppy guppy = new Guppy(time, 200.0, 200.0);
        Point max = new Point(500.0, 500.0);

        for (int i = 0; i < 100; i++) {
            guppy.moveRandom(time, max);
            time += 1000;
        }

        assertEquals(true, guppy.isInsideAquarium(guppy.getX(), guppy.getY(), max));
        assertEquals(true, (guppy.getLastChangeDir() > 0));
    }
}