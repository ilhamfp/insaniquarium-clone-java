package com.ArkavQuarium;

import java.lang.Math;

public class Piranha extends Fish {

    private int price;

    public Piranha(double time, double x, double y) {
        super(time, x, y);
        price = 100;
        setSpeed(getSpeed() + 0.5);
    }

    @Override
    public void moveRandom(double time, Point max) {
        double moveX, moveY, newRad;
        boolean directionChanged = false;

        if (isTimeToChangeDirection(time)) {
            directionChanged = true;
            newRad = createDirection(getX(), getY(), max);
            setDirectionRad(newRad);
        } else {
            moveX = speed * Math.cos(directionRad);
            moveY = speed * Math.sin(directionRad);
            if (!isInsideAquarium((getX() + moveX), (getY() + moveY), max)) {
                directionChanged = true;
                newRad = createDirection(getX(), getY(), max);
                setDirectionRad(newRad);
            }
        }

        if (directionChanged) {
            lastChangeDir = time;
            moveX = speed * Math.cos(newRad);
            moveY = speed * Math.sin(newRad);
            if (moveX > 0) { setDirection(1); }
            else { setDirection(0); }
        }

        setX(getX() + moveX);
        setY(getY() + moveY);
    }

    public void moveToEat(LinkedList<Guppy> listguppy, Guppy guppy, double time) {
        if (guppy.findDistance(new Point(getX(), getY())) <= speed) {
            setX(guppy.getX());
            setY(guppy.getY());
            lastEaten = time;
            foodCount++;
            if ((foodCount % foodThreshold) == 0 && growthStep < 3) {
                growthStep++;
            }
            listguppy.remove(food);
        } else {
            double rad = Math.atan2((guppy.getY() - getY()), (guppy.getX() - getX()));
            double moveX = speed * Math.cos(rad);
            double moveY = speed * Math.sin(rad);

            setX(getX() + moveX);
            setY(getY() + moveY);
            lastChangeDir = time;
            directionRad = rad;
            if (moveX > 0) { direction = 1; }
            else { direction = 0; }
        }
    }

}
