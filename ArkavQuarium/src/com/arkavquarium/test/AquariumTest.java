package com.arkavquarium.test;

import com.arkavquarium.Aquarium;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AquariumTest {

    @Test
    public void testConstructorAquarium() {
        Aquarium aquarium = new Aquarium(680,720);

        assertEquals(true,aquarium.getListCoin().isEmpty());
        assertEquals(true,aquarium.getListFood().isEmpty());
        assertEquals(true,aquarium.getListGuppy().getSize()==1);
        assertEquals(200,aquarium.getMoney());
        assertEquals(680,aquarium.getMaxLocation().getX());
        assertEquals(720,aquarium.getMaxLocation().getY());
        assertEquals(0,aquarium.getStateGame());
    }

    @Test
    public void testAquariumWithOneGuppyMoveUntilAtBottom() {
        Aquarium aquarium = new Aquarium(680,720);
        assertEquals(0,aquarium.getStateGame());

    }

}