package com.arkavquarium;

import static com.arkavquarium.Constants.ITEM_SPEED;

public class Item extends Point {
  private double speed;

  public Item() {
    super(-1,-1);
    speed = ITEM_SPEED;
  }

  public Item(double x, double y) {
    super(x,y);
    speed = ITEM_SPEED;
  }

  public void move(double maks) {
    double gerakSejauh = Math.min(super.getY() + speed,maks);
    super.setLocation(getX(),gerakSejauh);
  }

  public boolean isAtBottom(double bottom) {
    return (super.getY() == bottom);
  }
}
