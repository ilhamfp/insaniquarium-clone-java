package com.arkavquarium;

import java.io.Serializable;

public class Point implements Serializable {
  private double absis;
  private double ordinat;

  public Point() {
    absis = 0.0;
    ordinat = 0.0;
  }

  public Point(double absis, double ordinat) {
    this.absis = absis;
    this.ordinat = ordinat;
  }

  public double getX() {
    return absis;
  }

  public double getY() {
    return ordinat;
  }

  public void setX(double absis) {
    this.absis = absis;
  }

  public void setY(double ordinat) {
    this.ordinat = ordinat;
  }

  public void setLocation(double absis, double ordinat) {
    this.absis = absis;
    this.ordinat = ordinat;
  }

  /**
   * @param Point yang ingin diukur jaraknya dengan.
   * @return Jarak antara titik .
   */
  public double findDistance(Point p) {
    double tempX = p.getX() - absis;
    double tempY = p.getY() - ordinat;
    return Math.sqrt(tempX * tempX + tempY * tempY);
  }

  public boolean equals(Point p) {
    return absis == p.getX() && ordinat == p.getY();
  }

}
