package com.javarush.task.task24.task2413;

public abstract class BaseObject {

    private double x;
    private double y;
    private double radius;

    public BaseObject(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public boolean intersects(BaseObject o){
       return Math.sqrt(Math.pow((o.getX() - x),2) + Math.pow( (o.getY() - y), 2)) <= Math.max(radius,o.radius);
    }

    abstract void draw(Canvas canvas);
    abstract void move();

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
