/*
import edu.princeton.cs.algs4.StdDraw;

public class RectHV {
    private double xmin, xmax, ymin, ymax;

    public RectHV(double xmin, double ymin,      // construct the rectangle [xmin, xmax] x [ymin, ymax]
                  double xmax, double ymax) {
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
        if (xmin > xmax || ymin > ymax) throw new IllegalArgumentException("Wrong value of the rectangle!");
    }      // throw an IllegalArgumentException if (xmin > xmax) or (ymin > ymax)

    public double xmin() {
        return xmin;
    }                          // minimum x-coordinate of rectangle

    public double ymin() {
        return ymin;
    }                           // minimum y-coordinate of rectangle

    public double xmax() {
        return xmax;
    }                           // maximum x-coordinate of rectangle

    public double ymax() {
        return ymax;
    }                          // maximum y-coordinate of rectangle

    public boolean contains(Point2D p) {
        return p.y() >= ymin() && p.y() <= ymax() && p.x() >= xmin() && p.x() <= xmax();

    }             // does this rectangle contain the point p (either inside or on boundary)?

    public boolean intersects(RectHV that) {
        return (that.ymin() >= this.ymin && that.ymin() <= this.ymax && that.ymax() >= this.ymax)
                || (that.ymax() >= this.ymin && that.ymax() <= this.ymax && that.ymin() <= this.ymin)
                || (that.xmin() >= this.xmin && that.xmin() <= this.xmax && that.xmax() >= this.xmax)
                || (that.xmax() >= this.xmin && that.xmax() <= this.xmax && that.xmin() <= this.xmin)
                || (that.xmax() >= this.xmin && that.xmax() <= this.xmax && that.xmin() >= this.xmin && that.ymax() >= this.ymax && that.ymin <= this.ymin)
                || (that.ymax() >= this.ymin && that.ymax() <= this.ymax && that.ymin() >= this.ymin && that.xmax() >= this.xmax && that.xmin <= this.xmin);
    }          // does this rectangle intersect that rectangle (at one or more points)?

    public double distanceTo(Point2D p) {
        Point2D pRec1 = new Point2D(xmin, ymax);
        Point2D pRec2 = new Point2D(xmax, ymax);
        Point2D pRec3 = new Point2D(xmin, ymin);
        Point2D pRec4 = new Point2D(xmax, ymin);
        if (contains(p)) return 0;
        if (p.x() >= xmin && p.x() <= xmax && p.y() > ymax) return p.y() - ymax;
        if (p.x() >= xmin && p.x() <= xmax && p.y() < ymin) return ymin - p.y();
        if (p.y() >= ymin && p.y() <= ymax && p.x() > xmax) return p.x() - xmax;
        if (p.y() >= ymin && p.y() <= ymax && p.x() < xmin) return xmin - p.x();
        if (p.x() < xmin && p.y() > ymax) return p.distanceTo(pRec1);
        if (p.x() > xmax && p.y() > ymax) return p.distanceTo(pRec2);
        if (p.x() < xmin && p.y() < ymin) return p.distanceTo(pRec3);
        else return p.distanceTo(pRec4);
    }     // Euclidean distance from point p to closest point in rectangle

    public double distanceSquaredTo(Point2D p) {
        return distanceTo(p) * distanceTo(p);
    }     // square of Euclidean distance from point p to closest point in rectangle

    public boolean equals(Object that) {
        if (that == this) return true;
        if (that == null) return false;
        if (that.getClass() != this.getClass()) return false;
        RectHV rh = (RectHV) that;
        if (rh.xmin() != this.xmin) return false;
        if (rh.xmax() != this.xmax) return false;
        if (rh.ymin() != this.ymin) return false;
        return rh.ymax() == this.ymax;
    }              // does this rectangle equal that object?

    public void draw() {
        StdDraw.point(xmin, ymin);
        StdDraw.point(xmin, ymax);
        StdDraw.point(xmax, ymin);
        StdDraw.point(xmax, ymax);
        StdDraw.line(this.xmin, this.ymin, this.xmax, this.ymin);
        StdDraw.line(this.xmin, this.ymin, this.xmin, this.ymax);
        StdDraw.line(this.xmax, this.ymax, this.xmin, this.ymax);
        StdDraw.line(this.xmax, this.ymax, this.xmax, this.ymin);
    }                           // draw to standard draw

    public String toString() {
        return "(" + xmin + ", " + ymin + ")\n(" + xmin + ", " + ymax + ")\n("
                + xmax + ", " + ymin + ")\n(" + xmax + ", " + ymax + ")";
    }

    */
/*public static void main(String[] args) {
        RectHV rhv = new RectHV(200, 400, 400, 600);
        Point2D p = new Point2D(250, 300);
        StdOut.println(rhv.contains(p));
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLUE);
        rhv.draw();
        StdDraw.show();
    }*//*

}                       // string representation

*/
