package com.ArkavQuarium;
import static com.ArkavQuarium.Constants.*;

public class Item extends Point{
    private double speed;

    public Item(){
        super(-1,-1);
        speed = ITEM_SPEED;
    }

    public Item(double x, double y) {
        super(x,y);
        speed = ITEM_SPEED;
    }

    void move(double maks){
        double gerakSejauh = Math.min(super.getY()+speed,maks);
        super.setLocation(getX(),gerakSejauh);
    }

    boolean isAtBottom(double bottom){
        return (super.getY() == bottom);
    }
}