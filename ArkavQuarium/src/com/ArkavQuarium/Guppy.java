package com.ArkavQuarium;

import java.lang.Math;
import static com.ArkavQuarium.Constants.*;

public class Guppy extends Fish {

    private int growthStep;
    private int foodCount;
    private int price;
    private int foodThreshold;
    private double lastCoinTime;
    private double coinInterval;

    public Guppy(double time, double x, double y) {
        super(time, x, y);

        growthStep = 1;
        foodCount = 0;
        lastCoinTime = 0;
        foodThreshold = GUPPY_FOOD_THRESHOLD;
        price = GUPPY_PRICE;
        coinInterval = GUPPY_COIN_INTERVAL;
    }

    public int getGrowthStep() {
        return growthStep;
    }

    public void setGrowthStep(int growthStep) {
        this.growthStep = growthStep;
    }

    public int getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
    }

    public double getLastCoinTime() {
        return lastCoinTime;
    }

    public void setLastCoinTime(double lastCoinTime) {
        this.lastCoinTime = lastCoinTime;
    }

    @Override
    public void moveRandom(double time, Point max) {
        double moveX, moveY, newRad;

        if (isTimeToChangeDirection(time)) {
            newRad = createDirection(getX(), getY(), max);
            setDirectionRad(newRad);

            lastChangeDir = time;
            moveX = speed * Math.cos(newRad);
            moveY = speed * Math.sin(newRad);
            if (moveX > 0) { setDirection(1); }
            else { setDirection(0); }

        } else {
            moveX = speed * Math.cos(directionRad);
            moveY = speed * Math.sin(directionRad);
            if (!isInsideAquarium((getX() + moveX), (getY() + moveY), max)) {
                newRad = createDirection(getX(), getY(), max);
                setDirectionRad(newRad);

                lastChangeDir = time;
                moveX = speed * Math.cos(newRad);
                moveY = speed * Math.sin(newRad);
                if (moveX > 0) { setDirection(1); }
                else { setDirection(0); }
            }
        }

        setX(getX() + moveX);
        setY(getY() + moveY);
    }

    public void moveToEat(LinkedList<Food> listfood, Food food, double time) {
        //System.out.println("MELBU COK");
        if (food.findDistance(new Point(getX(), getY())) <= speed) {
            setX(food.getX());
            setY(food.getY());
            lastEaten = time;
            foodCount++;
            if ((foodCount % foodThreshold) == 0 && growthStep < 3) {
                growthStep++;
            }
            listfood.remove(food);
        } else {
            double rad = Math.atan2((food.getY() - getY()), (food.getX() - getX()));
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

    public boolean isTimeToGiveCoin(double time) {
        boolean waktunya = (time - lastCoinTime) >= coinInterval;
        if (waktunya) { lastCoinTime = time; }
        return waktunya;
    }

}
