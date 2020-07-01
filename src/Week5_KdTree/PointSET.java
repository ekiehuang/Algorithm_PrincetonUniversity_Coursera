import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;

import java.util.TreeSet;

public class PointSET {
    private int size;
    private TreeSet<Point2D> pointSet;

    public PointSET() {
        pointSet = new TreeSet<Point2D>();
    }                               // construct an empty set of points

    private void assertPoint(Point2D p) {
        if (p == null) throw new IllegalArgumentException("No points");
    }

    private void assertRect(RectHV r) {
        if (r == null) throw new IllegalArgumentException("No Rectangle");
    }

    public boolean isEmpty() {
        return size() == 0;
    }                    // is the set empty?

    public int size() {
        return size;
    }                        // number of points in the set

    public void insert(Point2D p) {
        assertPoint(p);
        if (!contains(p)) pointSet.add(p);
    }            // add the point to the set (if it is not already in the set)

    public boolean contains(Point2D p) {
        assertPoint(p);
        return pointSet.contains(p);
    }         // does the set contain point p?

    public void draw() {
        for (Point2D p : pointSet) {
            p.draw();
        }
    }                       // draw all points to standard draw

    public Iterable<Point2D> range(RectHV rect) {
        assertRect(rect);
        Queue<Point2D> inPoint = new Queue<Point2D>();
        for (Point2D p : pointSet) {
            if (rect.contains(p)) inPoint.enqueue(p);
        }
        return inPoint;
    }       // all points that are inside the rectangle (or on the boundary)

    public Point2D nearest(Point2D p) {
        double minD = Double.POSITIVE_INFINITY;
        Point2D q = p;
        assertPoint(p);
        if (isEmpty()) return null;
        for (Point2D pt : pointSet) {
            if (p.distanceTo(pt) < minD) {
                minD = p.distanceTo(pt);
                q = pt;
            }
        }
        return q;
    }          // a nearest neighbor in the set to point p; null if the set is empty

    public static void main(String[] args) {
    }                // unit testing of the methods (optional)
}