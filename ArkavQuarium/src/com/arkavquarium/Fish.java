package com.arkavquarium;

import static com.arkavquarium.Constants.FISH_CHANGE_DIR_INTERVAL;
import static com.arkavquarium.Constants.FISH_FULL_CONSTRAINT;
import static com.arkavquarium.Constants.FISH_HUNGRY_CONSTRAINT;
import static com.arkavquarium.Constants.FISH_SPEED;

import java.lang.Math;
import java.util.Random;

public abstract class Fish extends Point {

  protected double speed;
  protected int direction;
  protected double lastEaten;
  protected double hungryConstraint;
  protected double fullConstraint;
  protected double changeDirInterval;
  protected double directionRad;
  protected double lastChangeDir;

  /**
   * constructor fish with parameter.
   * @param time = time it is created
   * @param x = absis
   * @param y = ordinat
   */
  public Fish(double time, double x, double y) {
    super(x, y);

    speed = FISH_SPEED;
    hungryConstraint = FISH_HUNGRY_CONSTRAINT;
    fullConstraint = FISH_FULL_CONSTRAINT;
    changeDirInterval = FISH_CHANGE_DIR_INTERVAL;
    lastEaten = time;
    lastChangeDir = time;

    Random random = new Random();
    double degree = Math.abs((random.nextDouble() * random.nextInt() * 10000.0) % 361);
    directionRad = Math.toRadians(degree);

    if ((degree <= 90) || (degree >= 270)) {
      direction = 1;
    } else {
      direction = 0;
    }
  }

  public double getSpeed() {
    return speed;
  }

  public void setSpeed(double speed) {
    this.speed = speed;
  }

  public int getDirection() {
    return direction;
  }

  public void setDirection(int direction) {
    this.direction = direction;
  }

  public double getLastEaten() {
    return lastEaten;
  }

  public void setLastEaten(double lastEaten) {
    this.lastEaten = lastEaten;
  }

  public double getHungryConstraint() {
    return hungryConstraint;
  }

  public void setHungryConstraint(double hungryConstraint) {
    this.hungryConstraint = hungryConstraint;
  }

  public double getFullConstraint() {
    return fullConstraint;
  }

  public void setFullConstraint(double fullConstraint) {
    this.fullConstraint = fullConstraint;
  }

  public double getChangeDirInterval() {
    return changeDirInterval;
  }

  public void setChangeDirInterval(double changeDirInterval) {
    this.changeDirInterval = changeDirInterval;
  }

  public double getDirectionRad() {
    return directionRad;
  }

  public void setDirectionRad(double directionRad) {
    this.directionRad = directionRad;
  }

  public double getLastChangeDir() {
    return lastChangeDir;
  }

  public void setLastChangeDir(double lastChangeDir) {
    this.lastChangeDir = lastChangeDir;
  }

  public abstract void moveRandom(double time, Point max);

  /**
   * create new direction for fish.
   * @param x = absis now
   * @param y = ordinat now
   * @param max = maximal point that cannot be passed
   * @return direction
   */
  public double createDirection(double x, double y, Point max) {
    Random random = new Random();
    double rad = Math.toRadians(Math.abs((random.nextDouble() * random.nextInt() * 10000.0) % 361));
    double moveX = speed * Math.cos(rad);
    double moveY = speed * Math.sin(rad);

    while (!isInsideAquarium((x + moveX), (y + moveY), max)) {
      rad = Math.toRadians(Math.abs((random.nextDouble() * random.nextInt() * 10000.0) % 361));
      moveX = speed * Math.cos(rad);
      moveY = speed * Math.sin(rad);
    }

    return rad;
  }

  /**
   * check if fish inside aquarium.
   * @param x = absis fish now
   * @param y = ordinat fish now
   * @param max = maximal point that cant be passed
   * @return true if it is inside aquarium, either false
   */
  public boolean isInsideAquarium(double x, double y, Point max) {
    return x >= 20.0
          && x <= (max.getX() - 20.0)
          && y >= 150.0
          && y <= (max.getY() - 40.0);
  }

  public boolean isHungry(double time) {
    return (time - lastEaten) >= fullConstraint;
  }

  public boolean isTimeToDie(double time) {
    return (time - lastEaten) >= hungryConstraint;
  }

  public boolean isTimeToChangeDirection(double time) {
    return (time - lastChangeDir) >= changeDirInterval;
  }
}
