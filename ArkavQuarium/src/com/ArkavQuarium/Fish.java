package com.ArkavQuarium;

import java.util.Random;
import java.lang.Math;
import static com.ArkavQuarium.Constants.*;

public abstract class Fish extends Point {

    protected double speed;
    protected int direction;
    protected long lastEaten;
    protected long hungryConstraint;
    protected long fullConstraint;
    protected long changeDirInterval;
    protected double directionRad;
    protected long lastChangeDir;

    public Fish(long time, double x, double y) {
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

    public long getLastEaten() {
        return lastEaten;
    }

    public void setLastEaten(long lastEaten) {
        this.lastEaten = lastEaten;
    }

    public long getHungryConstraint() {
        return hungryConstraint;
    }

    public void setHungryConstraint(long hungryConstraint) {
        this.hungryConstraint = hungryConstraint;
    }

    public long getFullConstraint() {
        return fullConstraint;
    }

    public void setFullConstraint(long fullConstraint) {
        this.fullConstraint = fullConstraint;
    }

    public long getChangeDirInterval() {
        return changeDirInterval;
    }

    public void setChangeDirInterval(long changeDirInterval) {
        this.changeDirInterval = changeDirInterval;
    }

    public double getDirectionRad() {
        return directionRad;
    }

    public void setDirectionRad(double directionRad) {
        this.directionRad = directionRad;
    }

    public long getLastChangeDir() {
        return lastChangeDir;
    }

    public void setLastChangeDir(long lastChangeDir) {
        this.lastChangeDir = lastChangeDir;
    }

    public abstract void moveRandom(long time, Point max);

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

    public boolean isHungry(long time) {
        return (time - lastEaten) >= fullConstraint;
    }

    public boolean isTimeToDie(long time) {
        return (time - lastEaten) >= hungryConstraint;
    }

    public boolean isTimeToChangeDirection(long time) {
        return (time - lastChangeDir) >= changeDirInterval;
    }

}
