package com.ArkavQuarium.test;

import com.ArkavQuarium.Aquarium;
import com.ArkavQuarium.Food;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AquariumTest {

    @Test
    public void testConstructorAquarium() {
        Aquarium aquarium = new Aquarium(680,720);

        assertEquals(true,aquarium.getListCoin().isEmpty());
        assertEquals(true,aquarium.getListFood().isEmpty());
        assertEquals(true,aquarium.getListGuppy().isEmpty());
        assertEquals(200,aquarium.getMoney());
        assertEquals(680,aquarium.getMaxLocation().getX());
        assertEquals(720,aquarium.getMaxLocation().getY());
        assertEquals(0,aquarium.getStateGame());
    }

}