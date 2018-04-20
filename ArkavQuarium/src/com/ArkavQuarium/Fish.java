package com.ArkavQuarium;

import java.util.Random;
import java.lang.Math;

public abstract class Fish extends Point {

    protected double speed;
    protected int direction;
    protected double lastEaten;
    protected double hungryConstraint;
    protected double fullConstraint;
    protected double changeDirInterval;
    protected double directionRad;
    protected double lastChangeDir;

    public Fish(double time, double x, double y) {
        super(x, y);

        speed = 1.25;
        hungryConstraint = 10.0;
        fullConstraint = 6.0;
        changeDirInterval = 2.5;
        lastEaten = time;
        lastChangeDir = time;

        Random random = new Random();
        double degree = Math.abs((random.nextDouble() * random.nextInt() * 10000.0) % 361);
        directionRad = Math.toRadians(degree);

        if ((degree <= 90) || (degree >= 270)) {
            direction = 1;
        } else { direction = 0; }
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
