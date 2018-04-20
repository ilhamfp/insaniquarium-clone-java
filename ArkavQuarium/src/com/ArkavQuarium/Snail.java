package com.ArkavQuarium;

public class Snail extends Point{

    private int direction;
    private double speed;

    // Default Constructor
    public Snail(){
        super(0.0,0.0);
        speed = 0.5;
        direction = 1;
    }

    // Constructor
    public  Snail(Point P){
        super(P.getX()/2, P.getY()-40.0);
        speed = 0.5;
        direction = 1;
    }

    /*** getter setter ***/
    public int getDirection(){
        return direction;
    }

    public void setDirection( int direction){
        this.direction = direction;
    }

    public double getSpeed(){
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void move(Point P, Bool eat){
        double distance = Math.abs(P.getX() - getX());
        if(direction == 1 && P.getX() < getX())
            direction = 0;
        else if(direction == 0 && P.getX() > getX())
            direction = 1;

        if(distance <= speed)
            setX(P.getX());
        else{
            if(direction == 1)
                setX(speed + getX());
            else
                setX(getX()- speed);
        }
        if(this.equals(P))
            eat.setValue(true);
        else
            eat.setValue(false);
    }

    public int takeCoin(LinkedList<Coin> listCoin, Coin coin){
        int value = coin.getValue();
        listCoin.remove(coin);
        return value;
    }
}
