package com.ArkavQuarium;
import java.util.Random;
import java.util.Timer;

public class Aquarium {
    protected Point maxLocation = new Point();
    protected LinkedList<Guppy> listGuppy = new LinkedList<Guppy>();
    protected LinkedList<Piranha> listPiranha = new LinkedList<Piranha>();
    protected LinkedList<Coin> listCoin = new LinkedList<Coin>();
    protected LinkedList<Food> listFood = new LinkedList<Food>();
    protected Snail garry;
    protected int egg;
    protected int money;
    protected int guppyPeriod;
    protected int piranhaPeriod;
    protected int foodPeriod;
    protected Timer currentTime;

    public Aquarium(int screenWidth, int screenHeight) {
        currentTime = new Timer();
        maxLocation.setLocation((double)screenWidth, (double)screenHeight);
        garry = new Snail(new Point((double)screenWidth/2,(double)screenHeight - 40.0));
        egg = 0;
        guppyPeriod = 10;
        foodPeriod = 10;
        piranhaPeriod = 10;
        money = 200;

        // init draw
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public double getCurrentTime() {
        // set current time
        return currentTime;
    }

    public Point getMaxLocation() {
        return maxLocation;
    }

    public int getMoney() {
        return money;
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

    public void run() {
        // implementasi draw
    }

    public void createGuppy() {
        Random rand = new Random();
        int x = rand.nextInt((int)maxLocation.getX()) + 1;
        int y = rand.nextInt((int)maxLocation.getY()) + 1;
        Guppy guppy = new Guppy(x, y, getCurrentTime());
        listGuppy.add(guppy);
    }

    public void createPiranha() {
        Random rand = new Random();
        int x = rand.nextInt((int)maxLocation.getX()) + 1;
        int y = rand.nextInt((int)maxLocation.getY()) + 1;
        Piranha piranha = new Piranha(x, y, getCurrentTime());
        listPiranha.add(piranha);
    }

    public void createFood(Point p) {
        Food food = new Food(p);
        listFood.add(food);
    }

    public void createCoin(Point p, int level) {
        Coin coin = new Coin(p,level);
        listCoin.add(coin);
    }

    public Point getClosestFood(Point p) {
        if (!listFood.isEmpty()) {
            Point temp = new Point(listFood.get(0).getX(), listFood.get(0).getY());
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

    public Point getClosestCoin(Point p) {
        if (!listCoin.isEmpty()) {
            Point temp = new Point(listCoin.get(0).getX(), listCoin.get(0).getY());
            for (int i = 0; i < listCoin.getSize(); i++) {
                if (p.findDistance(listCoin.get(i)) < p.findDistance(temp)) {
                    temp.setX(listCoin.get(i).getX());
                    temp.setY(listCoin.get(i).getY());
                }
            }
        }
        return new Point(0,0);
    }

    public Point getClosestGuppy(Point p) {
        if (!listGuppy.isEmpty())
        {
            Point closest = new Point(listGuppy.get(1).getX(), listGuppy.get(1).getY());
            for (int i = 0; i < listGuppy.getSize(); i++) {
                if (p.findDistance(listGuppy.get(i)) < p.findDistance(closest))
                {
                    closest.setX(listGuppy.get(i).getX());
                    closest.setY(listGuppy.get(i).getY());
                }
            }
            return closest;
        }
        return new Point(0,0);
    }

}
