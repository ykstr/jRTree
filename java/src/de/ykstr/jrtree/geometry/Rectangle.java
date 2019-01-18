package de.ykstr.jrtree.geometry;

import java.util.Arrays;

public class Rectangle implements MinimalBoundingBox{
    private Point p1;
    private Point p2;

    public Rectangle(Point p1, Point p2) {
        if(p1 == null || p2 == null)throw new IllegalArgumentException();
        this.p1 = p1;
        this.p2 = p2;
    }

    public Rectangle(double x1, double y1, double x2, double y2){
        this(new Point(x1, y1), new Point(x2, y2));
    }

    public Point[] getAllCorners(){
        return new Point[]{p1,p2, new Point(p1.x, p2.y), new Point(p2.x, p1.y)};
    }

    public boolean contains(Point p){
        boolean insideX = p.x >= Math.min(p1.x, p2.x) && p.x <= Math.max(p1.x, p2.x);
        boolean insideY = p.y >= Math.min(p1.y, p2.y) && p.y <= Math.max(p1.y, p2.y);
        return insideX && insideY;
    }

    public boolean contains(Rectangle r){
        for(Point p : r.getAllCorners()){
            if(!contains(p))return false;
        }
        return true;
    }

    public boolean intersects(Rectangle r){
        for(Point p : r.getAllCorners()){
            if(contains(p))return true;
        }
        return false;
    }

    public void expandToContain(Point p){
        double p1xDistance = 0;
        double p1yDistance = 0;
        double p2xDistance = 0;
        double p2yDistance = 0;
        if(p1.xDistance(p) < p2.xDistance(p)){
            p1xDistance = p.x-p1.x;
        }else{
            p2xDistance = p.x-p2.x;
        }
        if(p1.yDistance(p) < p2.yDistance(p)){
            p1yDistance = p.y-p1.y;
        }else{
            p2yDistance = p.y-p2.y;
        }
        p1 = Point.move(p1, p1xDistance, p1yDistance);
        p2 = Point.move(p2, p2xDistance, p2yDistance);
    }

    public double getExpansionArea(Point p){
        double xDistance = p.x - (p1.xDistance(p) < p2.xDistance(p)?p1:p2).x;
        double yDistance = p.y - (p1.yDistance(p) < p2.yDistance(p)?p1:p2).y;
        return xDistance*height()+yDistance*width()+xDistance*yDistance;
    }

    public double getExpansionArea(Rectangle r){
        Rectangle temp = Rectangle.copy(this);
        temp.expandToContain(r);
        return area()-temp.area();
    }

    public void expandToContain(Rectangle r){
        expandToContain(r.p1);
        expandToContain(r.p2);
    }

    public double area(){
        return width()*height();
    }

    public double width(){
        return p1.xDistance(p2);
    }

    public double height(){
        return p1.yDistance(p2);
    }

    public static Rectangle copy(Rectangle r){
        return new Rectangle(r.p1, r.p2);
    }

    @Override
    public Rectangle getMBB() {
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Rectangle){
            Rectangle r = (Rectangle)obj;
            return (p1.equals(r.p1) && p2.equals(r.p2)) || (p1.equals(r.p2) && p2.equals(r.p1));
        }else return false;
    }

    @Override
    public String toString() {
        return Arrays.toString(getAllCorners());
    }
}
