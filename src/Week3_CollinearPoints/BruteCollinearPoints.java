/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    Point[] ps;
    ArrayList<LineSegment> lines;

    public BruteCollinearPoints(Point[] points) {
        assertPoints(points);
        int N=points.length;
        this.ps=new Point[N];
        for(int i=0;i<N;i++){
            ps[i]=points[i];
        }
        Arrays.sort(ps);
        lines=new ArrayList<LineSegment>();
        for (int k1 = 0; k1 < N - 3; k1++) {
            for (int k2 = k1 + 1; k2 < N-2; k2++) {
                for(int k3 = k2+1; k3 < N-1; k3++){
                    if(ps[k1].slopeTo(ps[k2])== ps[k1].slopeTo(ps[k3])) {
                        for (int k4 = k3 + 1; k4 < N; k4++) {
                            double sk = ps[k1].slopeTo(ps[k2]);
                            if (sk == ps[k1].slopeTo(ps[k2]) && sk == ps[k1].slopeTo(ps[k4])){
                                lines.add(new LineSegment(ps[k1], ps[k4]));
                        }
                    }
                }
            }
        }
    }}// finds all line segments containing 4 points

    private void assertPoints(Point[] points){
        if(points == null) throw new IllegalArgumentException("No points");
        for(int i=0;i<points.length-1;i++){
            for(int j=i+1;j<points.length;j++)
            if(points[i]==null || points[i].compareTo(points[j])==0)
                throw new IllegalArgumentException("Null or Duplicate points");
        }
    }

    public int numberOfSegments(){return lines.size();};     // the number of line segments

    public LineSegment[] segments(){
        LineSegment[] s =new LineSegment[lines.size()];
        return lines.toArray(s);
    }                 // the line segments

    public static void main(String[] args){

        Point[] points = new Point[10];
        points[0] = new Point(110, 220);
        points[1] = new Point(200, 400);
        points[2] = new Point(300, 600);
        points[3] = new Point(400, 800);
        points[4] = new Point(5000, 10000);
        points[5] = new Point(100, 300);
        points[6] = new Point(180, 540);
        points[7] = new Point(240, 720);
        points[8] = new Point(1000, 2000);
        points[9] = new Point(100, 200);


        // draw points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        for(Point p : points){
            p.draw();
        }
        StdDraw.show();

        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for(LineSegment sg : collinear.segments()){
            StdOut.println(sg);
            sg.draw();
        }
        StdDraw.show();
    }
}
