package com.arkavquarium.test;

import com.arkavquarium.Aquarium;
import com.arkavquarium.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AquariumTest {

  @Test
  public void testConstructorAquarium() {
    Aquarium aquarium = new Aquarium(680, 720);

    assertEquals(true, aquarium.getListCoin().isEmpty());
    assertEquals(true, aquarium.getListFood().isEmpty());
    assertEquals(true, aquarium.getListGuppy().getSize() == 0);
    assertEquals(200, aquarium.getMoney());
    assertEquals(680, aquarium.getMaxLocation().getX());
    assertEquals(720, aquarium.getMaxLocation().getY());
    assertEquals(0, aquarium.getStateGame());
  }

  @Test
  public void testAquariumWithOneGuppyMoveUntilAtBottom() {
    Aquarium aquarium = new Aquarium(680, 720);
    assertEquals(0, aquarium.getStateGame());
  }

  @Test
  public void testCreateCoin() {
    Aquarium aquarium = new Aquarium(680, 720);
    Point coin = new Point(10.0, 10.0);
    aquarium.createCoin(coin, 1);
    assertEquals(coin.getX(), aquarium.getListCoin().get(0).getX());
    assertEquals(coin.getY(), aquarium.getListCoin().get(0).getY());
    assertEquals(1, aquarium.getListCoin().getSize());
  }

  @Test
  public void testCreateFood() {
    Aquarium aquarium = new Aquarium(680, 720);
    Point food = new Point(10.0, 10.0);
    aquarium.createFood(food);
    assertEquals(food.getX(), aquarium.getListFood().get(0).getX());
    assertEquals(food.getY(), aquarium.getListFood().get(0).getY());
    assertEquals(1, aquarium.getListFood().getSize());
  }

  @Test
  public void testCreateGuppy() {
    Aquarium aquarium = new Aquarium(680, 720);
    aquarium.createGuppy();
    assertEquals(1, aquarium.getListGuppy().getSize());
  }

  @Test
  public void testGetClosestCoin() {
    Aquarium aquarium = new Aquarium(680, 720);
    Point coin = new Point(10.0, 10.0);
    aquarium.createCoin(coin, 1);
    Point closest = aquarium.getClosestCoin(new Point(11.0, 11.0));
    assertEquals(closest.getX(), coin.getX());
    assertEquals(closest.getY(), coin.getY());
  }

  @Test
  public void testGetCLosestGuppy() {
    Aquarium aquarium = new Aquarium(680, 720);
    aquarium.createGuppy();
    Point closest = aquarium.getClosestGuppy(new Point(11.0, 11.0));
    assertEquals(closest.getX(), aquarium.getListGuppy().get(0).getX());
    assertEquals(closest.getY(), aquarium.getListGuppy().get(0).getY());
  }

  @Test
  public void testGetClosestFood() {
    Aquarium aquarium = new Aquarium(680, 720);
    Point food = new Point(10.0, 10.0);
    aquarium.createFood(food);
    Point closest = aquarium.getClosestFood(new Point(11.0, 11.0));
    assertEquals(closest.getX(), food.getX());
    assertEquals(closest.getY(), food.getY());
  }

}