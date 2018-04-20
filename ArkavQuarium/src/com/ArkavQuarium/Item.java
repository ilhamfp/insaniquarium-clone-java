package com.ArkavQuarium;

public class Item extends Point{
    private double speed;

    public Item(){
        super(-1,-1);
        speed = 0.6;
    }

    public Item(double x, double y) {
        super(x,y);
        speed = 0.6;
    }

    void move(double maks){
        double gerakSejauh = Math.min(super.getY()+speed,maks);
        super.setLocation(getX(),gerakSejauh);
    }

    boolean isAtBottom(double bottom){
        return (super.getY() == bottom);
    }
}