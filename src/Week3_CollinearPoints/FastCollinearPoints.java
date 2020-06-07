/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private ArrayList<LineSegment> lines;
    private Point[] ps;

    public FastCollinearPoints(Point[] points) {
        assertPoints(points);
        lines = new ArrayList<LineSegment>();
        int N = points.length;
        ps = new Point[N];
        for (int i = 0; i < N; i++) {
            ps[i] = points[i];
        }
        Arrays.sort(ps);
        for (int i = 0; i < N - 1; i++) {
            Point[] others = new Point[N - i - 1];
            Double[] sl = new Double[i];
            for (int j = 0; j < N - i - 1; j++) {
                others[j] = ps[j + i + 1];
            }
            for (int k = 0; k < i; k++) {
                sl[k] = ps[i].slopeTo(ps[k]);
            }
            Arrays.sort(sl);
            Arrays.sort(others, ps[i].slopeOrder());

            addSegment(sl, ps[i], others);
        }

    }// finds all line segments containing 4 or more points

    private void addSegment(Double[] sl, Point p, Point[] others) {
        int count = 1;
        double bgSlope = p.slopeTo(others[0]);


        for (int i = 1; i < others.length; i++) {
            double slopeOth = p.slopeTo(others[i]);
            if (slopeOth == bgSlope) count++;
            else {
                if (count >= 3 && !Subseg(bgSlope, sl)) {
                    lines.add(new LineSegment(p, others[i - 1]));
                }
                count = 1;
            }
            bgSlope = slopeOth;
        }

        if (count >= 3 && !Subseg(bgSlope, sl)) {
            lines.add(new LineSegment(p, others[others.length - 1]));
        }
    }

    private boolean Subseg(double bgSlope, Double[] sl) {
        int lo = 0;
        int hi = sl.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (bgSlope < sl[mid]) hi = mid - 1;
            else if (bgSlope > sl[mid]) lo = mid + 1;
            else return true;
        }
        return false;
    }


    private void assertPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("No points");

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new IllegalArgumentException("Null points");
        }

        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++)
                if (points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException("Duplicate points");
        }

    }

    public int numberOfSegments() {
        return lines.size();
    }      // the number of line segments

    public LineSegment[] segments() {
        LineSegment[] se = new LineSegment[lines.size()];
        return lines.toArray(se);
    }                // the line segments

    public static void main(String[] args) {

        Point[] points = new Point[10];
        points[0] = new Point(110, 330);
        points[1] = new Point(200, 400);
        points[2] = new Point(300, 600);
        points[3] = new Point(4000, 8000);
        points[4] = new Point(500, 1000);
        points[5] = new Point(100, 300);
        points[6] = new Point(180, 540);
        points[7] = new Point(240, 720);
        points[8] = new Point(1000, 3000);
        points[9] = new Point(1000, 2800);


        // draw points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment sg : collinear.segments()) {
            StdOut.println(sg);
            sg.draw();
        }
        StdDraw.show();
    }
}
