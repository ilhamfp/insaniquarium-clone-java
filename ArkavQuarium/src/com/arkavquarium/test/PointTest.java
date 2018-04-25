package com.arkavquarium.test;

import com.arkavquarium.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void findDistance() {
        Point p1 = new Point();
        Point p2 = new Point(3,4);
        double distance = p1.findDistance(p2);
        assertEquals(5.0, distance);
    }

    @Test
    void equals() {
        Point p1 = new Point();
        Point p2 = new Point();
        assertEquals(true, p1.equals(p2));
    }
}