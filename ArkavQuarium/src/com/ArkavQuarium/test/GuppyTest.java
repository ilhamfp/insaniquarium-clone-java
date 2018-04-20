package com.ArkavQuarium.test;

import com.ArkavQuarium.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import com.ArkavQuarium.Guppy;
import com.ArkavQuarium.Food;
import com.ArkavQuarium.LinkedList;

class GuppyTest {

    @Test
    public void testConstructorGuppy() {
        Guppy g = new Guppy(20.0, 30.0, 40.0);

        double x = 30.0;
        double y = 40.0;
        assertEquals(x, g.getX());
        assertEquals(y, g.getY());
    }

    @Test
    public void testMoveToEat() {
        double time = 0.0;
        Guppy g = new Guppy(time, 30.0, 40.0);
        Food food = new Food(new Point(20.0, 30.0));
        LinkedList<Food> listOfFood = new LinkedList<>();
        listOfFood.add(food);

        while (!listOfFood.isEmpty()) {
            g.moveToEat(listOfFood, food, time);
//            time++;
        }

        assertEquals(true, listOfFood.isEmpty());
        assertEquals(20.0, g.getX());
        assertEquals(30.0, g.getY());
    }

//    @Test
//    public void testMoveRandom() {
//        double time = 0.0;
//        Guppy g = new Guppy(time, 20.0, 20.0);
//        Point max = new Point(40.0, 200.0);
//        for (int i = 0; i < 50; i++) {
//            g.moveRandom(time,);
//        }
//    }

}