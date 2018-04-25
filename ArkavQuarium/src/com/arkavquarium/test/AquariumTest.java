package com.arkavquarium.test;

import com.arkavquarium.Aquarium;
import com.arkavquarium.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AquariumTest {

  @Test
  public void testConstructorAquarium() {
    Aquarium aquarium = new Aquarium(680,720);

    assertEquals(true,aquarium.getListCoin().isEmpty());
    assertEquals(true,aquarium.getListFood().isEmpty());
    assertEquals(true,aquarium.getListGuppy().getSize() == 0);
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

  public void testGetClosestCoin() {
    Aquarium aquarium = new Aquarium(680,720);
    Point coin = new Point(10.0,10.0);
    aquarium.createCoin(coin, 1);
    Point closest = aquarium.getClosestCoin(new Point(11.0, 11.0));
    assertEquals(closest.getX(), );
  }

}