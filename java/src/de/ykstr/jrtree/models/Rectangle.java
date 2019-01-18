package de.ykstr.jrtree.models;

public class Rectangle implements MinimalBoundingBox{
    private Point p1;
    private Point p2;

    public Rectangle(Point p1, Point p2) {
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

    @Override
    public Rectangle getMBB() {
        return this;
    }
}
