package com.arkavquarium;

import static com.arkavquarium.Constants.COIN_BASE_VALUE;

public class Coin extends Item {
  private int baseVal;
  private int value;

  /**
   * Constructor coin.
   */
  public Coin() {
    super(-1,-1);
    baseVal = COIN_BASE_VALUE;
    value = 0;
  }

  /**
   * Constructor coin.
   * @param p poin tempat coin dihasilkan
   * @param level level untuk menentukan coin
   */
  public Coin(Point p, int level) {
    super(p.getX(),p.getY());
    baseVal = COIN_BASE_VALUE;
    value = baseVal * level;
  }

  // setter untuk nilai value
  public void setValue(int val) {
    value = val;
  }

  // getter untuk nilai value
  public int getValue() {
    return value;
  }

  public int getBaseVal() {
    return baseVal;
  }
}
