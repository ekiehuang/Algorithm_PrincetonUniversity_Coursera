/*
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class Point2D implements Comparable<Point2D> {
    private double x, y, distance;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }         // construct the point (x, y)

    public double x() {
        return x;
    }                         // x-coordinate

    public double y() {
        return y;
    }                            // y-coordinate

    public double distanceTo(Point2D that) {
        return distance = Math.sqrt(Math.pow((that.y - this.y), 2) + Math.pow((that.x - this.x), 2));
    }        // Euclidean distance between two points

    public double distanceSquaredTo(Point2D that) {
        return distanceTo(that) * distanceTo(that);
    }  // square of Euclidean distance between two points

    public int compareTo(Point2D that) {
        if (y < that.y || (y == that.y && x < that.x)) {
            return -1;
        } else if (y == that.y && x == that.x) {
            return 0;
        } else {
            return 1;
        }
    }

    public Comparator<Point2D> X_ORDER() {
        return new X_order();
    }

    private static class X_order implements Comparator<Point2D> {
        @Override
        public int compare(Point2D p, Point2D w) {
            return Double.compare(p.x, w.x);
        }
    }

    public Comparator<Point2D> Y_ORDER() {
        return new Y_order();
    }

    private static class Y_order implements Comparator<Point2D> {
        @Override
        public int compare(Point2D p, Point2D w) {
            return Double.compare(p.x, w.x);
        }
    }

    // for use in an ordered symbol table

    public boolean equals(Object that) {
        if (that == this) return true;
        if (that == null) return false;
        if (that.getClass() != this.getClass()) return false;
        Point2D p = (Point2D) that;
        if (p.x != this.x) return false;
        return p.y == this.y;
    }              // does this point equal that object?

    public void draw() {
        StdDraw.point(x, y);
    }                           // draw to standard draw

    public String toString() {
        return "(" + x + ", " + y + ")";
    }                     // string representation

    public static void main(String[] args) {
        Point2D p = new Point2D(500, 300);
        Point2D o = new Point2D(100, 600);

        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLUE);
        p.draw();
        o.draw();
        StdDraw.show();

        StdDraw.setPenColor(StdDraw.MAGENTA);

        //p.drawTo(o);
        StdDraw.show();
        StdOut.println(p.toString() + o.toString() + "\nthe distance is " + p.distanceTo(o));
    }
}

*/
