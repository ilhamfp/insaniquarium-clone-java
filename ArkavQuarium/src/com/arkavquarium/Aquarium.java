package com.arkavquarium;

import static com.arkavquarium.Constants.GUPPY_PRICE;

import java.io.Serializable;
import java.util.Random;

public class Aquarium implements Serializable {

  private Point maxLocation = new Point();
  private LinkedList<Guppy> listGuppy = new LinkedList<Guppy>();
  private LinkedList<Piranha> listPiranha = new LinkedList<Piranha>();
  private LinkedList<Coin> listCoin = new LinkedList<Coin>();
  private LinkedList<Food> listFood = new LinkedList<Food>();
  private Snail garry;
  private int egg;
  private int money;
  private int guppyPeriod;
  private int piranhaPeriod;
  private int foodPeriod;
  private long startTime;

  /**
   * constructor aquarium with parameter.
   * @param screenWidth = width of aquarium
   * @param screenHeight = height of aquarium
   */
  public Aquarium(int screenWidth, int screenHeight) {
    startTime = System.nanoTime();
    maxLocation.setLocation((double)screenWidth, (double)screenHeight);
    egg = 0;
    guppyPeriod = 10;
    foodPeriod = 10;
    piranhaPeriod = 10;
    money = 200;

    garry = new Snail(new Point((double)screenWidth / 2,(double)screenHeight - 68));
  }

  public void setMoney(int money) {
    this.money = money;
  }

  public void setEgg(int egg) {
    this.egg = egg;
  }

  /**
   * get time now.
   * @return time now
   */
  public double getCurrentTime() {
    long estimatedTime = System.nanoTime() - startTime;

    double seconds = (double)estimatedTime / 1000000000.0;
    return seconds;
  }

  public int getEgg() {
    return egg;
  }

  public Point getMaxLocation() {
    return maxLocation;
  }

  public int getMoney() {
    return money;
  }

  public LinkedList<Piranha> getListPiranha() {
    return listPiranha;
  }

  public Snail getGarry() {
    return garry;
  }

  public LinkedList<Guppy> getListGuppy() {
    return listGuppy;
  }

  public LinkedList<Coin> getListCoin() {
    return listCoin;
  }

  public LinkedList<Food> getListFood() {
    return listFood;
  }

  /**
   * run the living in aquarium.
   */
  public void run() {
    moveGuppy();
    movePiranha();
    moveCoin();
    moveFood();
    moveSnail();
  }

  /**
   * create guppy in aquarium.
   */
  public void createGuppy() {

    Random rand = new Random();
    int x = rand.nextInt((int)maxLocation.getX() - 150 + 1) + 75;
    int y = rand.nextInt((int)maxLocation.getY() - 250 + 1) + 150;
    Guppy guppy = new Guppy(getCurrentTime(), x, y);
    listGuppy.add(guppy);
  }

  /**
   * create piranha in aquarium.
   */
  public void createPiranha() {

    Random rand = new Random();
    int x = rand.nextInt((int)maxLocation.getX() - 150 + 1) + 75;
    int y = rand.nextInt((int)maxLocation.getY() - 250 + 1) + 150;
    Piranha piranha = new Piranha(getCurrentTime(), x, y);
    listPiranha.add(piranha);
  }

  public void createFood(Point p) {
    Food food = new Food(p);
    listFood.add(food);
  }

  /**
   * create coin in aquarium.
   * @param p = coordinate where it is created
   * @param level = level of the coin
   */
  public void createCoin(Point p, int level) {

    Coin coin = new Coin(p,level);
    listCoin.add(coin);
  }

  /**
   * get closest food from coordinate p.
   * @param p = coordinate now
   * @return coordinate of closest food
   */
  public Point getClosestFood(Point p) {
    if (!listFood.isEmpty()) {
      Point temp = new Point(listFood.get(0).getX(), listFood.get(0).getY());
      for (int i = 0; i < listFood.getSize(); i++) {
        if (p.findDistance(listFood.get(i)) < p.findDistance(temp)) {
          temp.setX(listFood.get(i).getX());
          temp.setY(listFood.get(i).getY());
        }
      }
      return temp;
    }
    return new Point(0,0);
  }

  /**
   * get closest coin from coordinate p.
   * @param p = coordinate now
   * @return coordinate of closest coin
   */
  public Point getClosestCoin(Point p) {
    if (!listCoin.isEmpty()) {
      Point temp = new Point(listCoin.get(0).getX(), listCoin.get(0).getY());
      for (int i = 0; i < listCoin.getSize(); i++) {
        if (p.findDistance(listCoin.get(i)) < p.findDistance(temp)) {
          temp.setX(listCoin.get(i).getX());
          temp.setY(listCoin.get(i).getY());
        }
      }
      return temp;
    }
    return new Point(0,0);
  }

  /**
   * get closest guppy from coordinate p.
   * @param p = coordinate now
   * @return coordinate of closest guppy
   */
  public Point getClosestGuppy(Point p) {
    if (!listGuppy.isEmpty()) {
      Point closest = new Point(listGuppy.get(1).getX(), listGuppy.get(1).getY());
      for (int i = 0; i < listGuppy.getSize(); i++) {
        if (p.findDistance(listGuppy.get(i)) < p.findDistance(closest)) {
          closest.setX(listGuppy.get(i).getX());
          closest.setY(listGuppy.get(i).getY());
        }
      }
      return closest;
    }
    return new Point(0,0);
  }

  /**
   * method to move snail in the aquarium.
   */
  public void moveSnail() {
    if (!listCoin.isEmpty()) {
      Point locSnail = new Point(garry.getX(), garry.getY());
      Point closest = getClosestCoin(locSnail);
      Bool eat = new Bool();
      garry.move(closest, eat);
      if (eat.getValue()) {
        for (int i = 0; i < listCoin.getSize(); i++) {
          if (listCoin.get(i).getX() == closest.getX()
              && listCoin.get(i).getY() == closest.getY()) {
            garry.takeCoin(listCoin, listCoin.get(i));
          }
        }
      }
    }
  }

  /**
   * method to move Guppies in aquarium.
   */
  public void moveGuppy() {
    Point locGuppy = new Point();
    Point closest = new Point();

    for (int i = 0; i < listGuppy.getSize(); i++) {
      if (listGuppy.get(i).isTimeToDie(getCurrentTime())) {
        listGuppy.remove(listGuppy.get(i));
      } else {
        if (listGuppy.get(i).isTimeToGiveCoin(getCurrentTime())) {
          System.out.println("COIN COIN COIN");
          Coin coin = new Coin(new Point(listGuppy.get(i).getX(), listGuppy.get(i).getY()),
              listGuppy.get(i).getGrowthStep());
          listCoin.add(coin);
        }

        if (!listFood.isEmpty() && listGuppy.get(i).isHungry(getCurrentTime())) {
          locGuppy.setX(listGuppy.get(i).getX());
          locGuppy.setY(listGuppy.get(i).getY());
          Point temp = getClosestFood(locGuppy);
          closest.setX(temp.getX());
          closest.setY(temp.getY());

          for (int idx = 0; idx < listFood.getSize(); idx++) {
            if (listFood.get(idx).getX() == closest.getX()
                && listFood.get(idx).getY() == closest.getY()) {
              listGuppy.get(i).moveToEat(listFood, listFood.get(idx), getCurrentTime());
              break;
            }
          }
        } else {
          listGuppy.get(i).moveRandom(getCurrentTime(), maxLocation);
        }
      }
    }
  }

  /**
   * method to move piranha in aquarium.
   */
  public void movePiranha() {
    Point locPiranha = new Point();
    Point closest = new Point();

    for (int i = 0; i < listPiranha.getSize(); i++) {
      if (listPiranha.get(i).isTimeToDie(getCurrentTime())) {
        listPiranha.remove(listPiranha.get(i));
      } else {
        if (!listGuppy.isEmpty() && listPiranha.get(i).isHungry(getCurrentTime())) {
          Bool eat = new Bool();
          int guppyGrowth = 0;

          locPiranha.setX(listPiranha.get(i).getX());
          locPiranha.setY(listPiranha.get(i).getY());

          Point temp = getClosestGuppy(locPiranha);

          closest.setX(temp.getX());
          closest.setY(temp.getY());

          for (int idx = 0; idx < listGuppy.getSize(); idx++) {
            if (listGuppy.get(idx).getX() == closest.getX()
                && listGuppy.get(idx).getY() == closest.getY()) {
              guppyGrowth = listGuppy.get(idx).getGrowthStep();
              listPiranha.get(i).moveToEat(listGuppy, listGuppy.get(idx), getCurrentTime());

              if (eat.getValue()) {
                guppyGrowth++;
                Coin coin = new Coin(closest, guppyGrowth);
                listCoin.add(coin);
              }
              break;
            }
          }
        } else {
          listPiranha.get(i).moveRandom(getCurrentTime(), maxLocation);
        }
      }
    }
  }

  /**
   * method to move coins in aquarium.
   */
  public void moveCoin() {
    for (int i = 0; i < listCoin.getSize(); i++) {
      listCoin.get(i).move(maxLocation.getY() - 68);
    }
  }

  /**
   * method to move foods in aquarium.
   */
  public void moveFood() {
    for (int i = 0; i < listFood.getSize(); i++) {
      listFood.get(i).move(maxLocation.getY() - 68);
      if (listFood.get(i).isAtBottom(maxLocation.getY() - 68)) {
        listFood.remove(listFood.get(i));
      }
    }
  }

  /**
   * get state of the game.
   * @return state game
   */
  public int getStateGame() {
    if (egg == 3) {
      return 2;
    } else if ((money < GUPPY_PRICE) && listGuppy.isEmpty() && listCoin.isEmpty()) {
      return 1;
    } else {
      return 0;
    }
  }

}
