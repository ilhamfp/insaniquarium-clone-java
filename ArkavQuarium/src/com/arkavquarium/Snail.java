package com.arkavquarium;

public class Snail extends Point {

  private int direction;
  private double speed;

  /**
   * constructor snail.
   */
  public Snail() {
    super(0.0,0.0);
    speed = 0.5;
    direction = 1;
  }

  /**
   * constructor with parameter.
   * @param p = point where snail be placed
   */
  public Snail(Point p) {
    super(p.getX() / 2, p.getY());
    speed = 0.5;
    direction = 1;
  }

  /**
   * getter for direcntion.
   * @return direction
   */
  public int getDirection() {
    return direction;
  }

  public void setDirection(int direction) {
    this.direction = direction;
  }

  public double getSpeed() {
    return speed;
  }

  public void setSpeed(double speed) {
    this.speed = speed;
  }

  /**
   * move snail to Point p.
   * @param p = destination point
   * @param eat = can eat or not
   */
  public void move(Point p, Bool eat) {
    double distance = Math.abs(p.getX() - getX());
    if (direction == 1 && p.getX() < getX()) {
      direction = 0;
    } else if (direction == 0 && p.getX() > getX()) {
      direction = 1;
    }

    if (distance <= speed) {
      setX(p.getX());
    } else if (direction == 1) {
      setX(speed + getX());
    } else {
      setX(getX() - speed);
    }
    if (this.equals(P)) {
      eat.setValue(true);
    } else {
      eat.setValue(false);
    }
  }

  /**
   * method to take coin.
   * @param listCoin = listcoint from aquarium
   * @param coin = coin to be taken
   * @return value of the coin
   */
  public int takeCoin(LinkedList<Coin> listCoin, Coin coin) {
    int value = coin.getValue();
    listCoin.remove(coin);
    return value;
  }
}
