package de.ykstr.jrtree.models;

public class Point {

    public final double x;
    public final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public static Point move(Point p, double xDistance, double yDistance){
        return new Point(p.x+xDistance, p.y+yDistance);
    }

    public double xDistance(Point other){
        return Math.abs(other.x-x);
    }

    public double yDistance(Point other){
        return  Math.abs(other.y-y);
    }

    public double distance(Point other){
        return Math.sqrt(Math.pow(xDistance(other), 2)+Math.pow(yDistance(other), 2));
    }
}
