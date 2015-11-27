package org.ulco;

/**
 * Created by mistermagikarp on 27/11/15.
 */
public class Triangle extends GraphicsObject {
    Point m_origin;
    Point a, b, c;


    public Triangle(Point origin, Point a, Point b, Point c) {
        this.m_origin = origin;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public GraphicsObject copy() {
        return new Triangle(m_origin, a, b, c);
    }

    @Override
    public boolean isClosed(Point pt, double distance) {
        Double xc = (a.getX() + b.getX() + c.getX()) / 3;
        Double yc = (a.getY() + b.getY() + c.getY()) / 3;
        Point centre = new Point(xc, yc);
        double dist = Math.sqrt((centre.getX() - pt.getX()) * (centre.getX() - pt.getX()) + ((centre.getY() - pt.getY()) * (centre.getY() - pt.getY())));
        if (dist <= distance) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    void move(Point delta) {
        m_origin.move(delta);
    }

    @Override
    public String toJson() {
        return "{ type: triangle, origin: " + m_origin.toJson() + ", a: " + a.toJson() + ", b: " + b.toJson() + ", c: " + c.toJson() + " }";
    }

    @Override
    public String toString() {
        return "triangle[" + m_origin.toString() + "," + a.toString() + "," + b.toString() + "," + c.toString() + "]";
    }
}
