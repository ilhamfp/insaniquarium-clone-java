package com.ArkavQuarium.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import com.ArkavQuarium.Piranha;
import com.ArkavQuarium.Point;
import com.ArkavQuarium.Guppy;
import com.ArkavQuarium.LinkedList;

class PiranhaTest {
    @Test
    public void testConstructorPiranha() {
        Piranha p = new Piranha(20000, 30.0, 40.0);

        double x = 30.0;
        double y = 40.0;
        assertEquals(x, p.getX());
        assertEquals(y, p.getY());
    }

    @Test
    public void testMoveToEat() {
        long time = 0;
        Piranha piranha = new Piranha(time, 30.0, 40.0);
        Guppy guppy = new Guppy(time,50.0, 60.0);
        LinkedList<Guppy> listOfGuppy = new LinkedList<>();
        listOfGuppy.add(guppy);

        while (!listOfGuppy .isEmpty()) {
            piranha.moveToEat(listOfGuppy, guppy, time);
            time++;
        }

        assertEquals(true, listOfGuppy.isEmpty());
        assertEquals(50.0, piranha.getX());
        assertEquals(60.0, piranha.getY());
    }

    @Test
    public void testMoveRandom() {
        long time = 0;
        Piranha piranha = new Piranha(time, 200.0, 200.0);
        Point max = new Point(500.0, 500.0);

        for (int i = 0; i < 100; i++) {
            piranha.moveRandom(time, max);
            time += 1000;
        }

        assertEquals(true, piranha.isInsideAquarium(piranha.getX(), piranha.getY(), max));
        assertEquals(true, (piranha.getLastChangeDir() > 0));
    }
}