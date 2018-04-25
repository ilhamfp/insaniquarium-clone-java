package com.arkavquarium.test;

import com.arkavquarium.Snail;
import com.arkavquarium.Point;
import com.arkavquarium.Bool;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnailTest {

    @Test
    void testGetter(){
        Snail garry = new Snail();
        int direction = 1;
        double speed = 0.5;
        assertEquals(direction, garry.getDirection());
        assertEquals(speed, garry.getSpeed());
    }

    @Test
    void testConstructor() {
        Snail garry = new Snail();
        Point P = new Point();
        assertEquals(P.getX(), garry.getX());
        assertEquals(P.getY(), garry.getY());
    }

    @Test
    void testMove() {
        Snail garry = new Snail();
        Point P = new Point(100,100);
        Bool bol = new Bool();
        garry.move(P, bol);
        assertEquals(1, garry.getDirection());
        assertEquals(0.5, garry.getX());
    }
}