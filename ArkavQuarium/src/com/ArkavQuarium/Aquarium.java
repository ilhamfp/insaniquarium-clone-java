package com.ArkavQuarium;
import static com.ArkavQuarium.Constants.*;
import java.util.Random;
import javax.swing.*;
import java.awt.*;

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
    protected long startTime;

    protected JFrame frame;

    public Aquarium(int screenWidth, int screenHeight) {
        startTime = System.nanoTime();
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

    public long getCurrentTime() {
        long estimatedTime = System.nanoTime() - startTime;
        return estimatedTime;
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
        frame = new JFrame("Arkavquarium");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            
        }
    }

    public void createGuppy() {
        Random rand = new Random();
        int x = rand.nextInt((int)maxLocation.getX()) + 1;
        int y = rand.nextInt((int)maxLocation.getY()) + 1;
        Guppy guppy = new Guppy(getCurrentTime(), x, y);
        listGuppy.add(guppy);
    }

    public void createPiranha() {
        Random rand = new Random();
        int x = rand.nextInt((int)maxLocation.getX()) + 1;
        int y = rand.nextInt((int)maxLocation.getY()) + 1;
        Piranha piranha = new Piranha(getCurrentTime(), x, y);
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

    public void moveSnail() {
        if (!listCoin.isEmpty()) {
            Point locSnail = new Point(garry.getX(), garry.getY());
            Point closest = getClosestCoin(locSnail);
            Bool eat = new Bool();
            garry.move(closest, eat);
            if (eat.getValue()) {
                for (int i = 0; i < listCoin.getSize(); i++) {
                    if (listCoin.get(i).getX() == closest.getX() && listCoin.get(i).getY() == closest.getY()) {
                        garry.takeCoin(listCoin, listCoin.get(i));
                    }
                }
            }
        }
    }

    public void moveGuppy() {
        Point locGuppy = new Point();
        Point closest = new Point();

        for (int i = 0; i < listGuppy.getSize(); i++) {
            if (listGuppy.get(i).isTimeToDie(getCurrentTime())) {
                listGuppy.remove(listGuppy.get(i));
            } else {
                if (listGuppy.get(i).isTimeToGiveCoin(getCurrentTime())) {
                    Coin coin = new Coin(new Point(listGuppy.get(i).getX(), listGuppy.get(i).getY()), listGuppy.get(i).getGrowthStep());
                    listCoin.add(coin);
                }

                if (listFood.getSize() > 0 && listGuppy.get(i).isHungry(getCurrentTime())) {
                    locGuppy.setX(listGuppy.get(i).getX());
                    locGuppy.setY(listGuppy.get(i).getY());
                    Point temp = getClosestFood(locGuppy);
                    closest.setX(temp.getX());
                    closest.setY(temp.getY());

                    for (int idx = 0; idx < listFood.getSize(); idx++) {
                        if (listFood.get(idx).getX() == closest.getX() && listFood.get(idx).getY() == closest.getY() ) {
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
                        if (listGuppy.get(idx).getX() == closest.getX() && listGuppy.get(idx).getY() == closest.getY()) {
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

    public void moveCoin() {
        for (int i = 0; i < listCoin.getSize(); i++) {
            listCoin.get(i).move(maxLocation.getY());
        }
    }

    public void moveFood() {
        for (int i = 0; i < listFood.getSize(); i++) {
            listFood.get(i).move(maxLocation.getY());

            if (listFood.get(i).isAtBottom(maxLocation.getY())) {
                listFood.remove(listFood.get(i));
            }
        }
    }

//    public static void main(String[] args) {
//        new Aquarium(1080,720).run();
//    }

    public int getStateGame() {
        if (egg == 3) return 2;
        else if ((money < GUPPY_PRICE) && listGuppy.isEmpty() && listCoin.isEmpty()) return 1;
        else return 0;
    }

}
