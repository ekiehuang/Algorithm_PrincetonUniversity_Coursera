import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
    private KdNode root;

    private static class KdNode {
        private Point2D p;
        private RectHV rec;
        private int size;
        private KdNode left, right;

        public KdNode(Point2D p, RectHV r, int s) {
            this.p = p;
            this.rec = r;
            this.size = s;
        }
    }

   /* public KdTree() {
        root = null;
    } */                             // construct an empty set of points

    private void assertPoint(Point2D p) {
        if (p == null) throw new IllegalArgumentException("No points");
    }

    private void assertRect(RectHV r) {
        if (r == null) throw new IllegalArgumentException("No Rectangle");
    }

    public boolean isEmpty() {
        return root == null;
    }                    // is the set empty?

    public int size() {
        return number(root);
    }

    private int number(KdNode kn) {
        if (kn == null) return 0;
        return kn.size;
    }                        // number of points in the set

    public void insert(Point2D p) {
        assertPoint(p);
        if (root == null) root = new KdNode(p, new RectHV(0, 0, 1, 1), 1);
        else {
            double xmin = root.rec.xmin();
            double ymin = root.rec.ymin();
            double xmax = root.rec.xmax();
            double ymax = root.rec.ymax();
            root = insertV(root, p, xmin, ymin, xmax, ymax);
        }
    }             // add the point to the set (if it is not already in the set)

    private KdNode insertV(KdNode kn, Point2D p, double xmin, double ymin, double xmax, double ymax) {
        if (kn == null) return new KdNode(p, new RectHV(xmin, ymin, xmax, ymax), 1);
        if (kn.p.equals(p)) return kn;
        int cmp = Point2D.X_ORDER.compare(p, kn.p);
        if (cmp < 0) kn.left = insertH(kn.left, p, xmin, ymin, kn.p.x(), ymax);
        else kn.right = insertH(kn.right, p, kn.p.x(), ymin, xmax, ymax);
        kn.size = 1 + number(kn.left) + number(kn.right);
        return kn;
    }

    private KdNode insertH(KdNode kn, Point2D p, double xmin, double ymin, double xmax, double ymax) {
        if (kn == null) return new KdNode(p, new RectHV(xmin, ymin, xmax, ymax), 1);
        if (kn.p.equals(p)) return kn;
        int cmp = Point2D.Y_ORDER.compare(p, kn.p);
        if (cmp < 0) kn.left = insertV(kn.left, p, xmin, ymin, xmax, kn.p.y());
        else kn.right = insertV(kn.right, p, xmin, kn.p.y(), xmax, ymax);
        kn.size = 1 + number(kn.left) + number(kn.right);
        return kn;
    }

    public boolean contains(Point2D p) {
        KdNode kn = root;
        int level = 1;
        int cmp = 0;
        while (kn != null) {
            if (level % 2 != 0) {
                cmp = Point2D.X_ORDER.compare(p, kn.p);
            } else {
                cmp = Point2D.Y_ORDER.compare(p, kn.p);
            }
            if (cmp < 0) kn = kn.left;
            else if (cmp > 0) kn = kn.right;
            else return true;
            level++;
        }
        return false;
    }           // does the set contain point p?


    public void draw() {
        if (isEmpty()) return;
        draw(root, true);
    }

    private void draw(KdNode x, boolean vert) {
        if (x.left != null) draw(x.left, !vert);
        if (x.right != null) draw(x.right, !vert);

        x.p.draw();

        double xmin, ymin, xmax, ymax;
        if (vert) {
            StdDraw.setPenColor(StdDraw.RED);
            xmin = x.p.x();
            xmax = x.p.x();
            ymin = x.rec.ymin();
            ymax = x.rec.ymax();
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            xmin = x.rec.xmin();
            xmax = x.rec.xmax();
            ymin = x.p.y();
            ymax = x.p.y();
        }
        StdDraw.line(xmin, ymin, xmax, ymax);
    }                        // draw all points to standard draw

    public Iterable<Point2D> range(RectHV rect) {
        assertRect(rect);
        Queue<Point2D> q = new Queue<>();
        rangeSearch(root, rect, q);
        return q;
    }             // all points that are inside the rectangle (or on the boundary)

    private void rangeSearch(KdNode kn, RectHV rect, Queue<Point2D> q) {
        if (kn == null) return;
        if (!kn.rec.intersects(rect)) return;
        if (rect.contains(kn.p)) q.enqueue(kn.p);
        rangeSearch(kn.left, rect, q);
        rangeSearch(kn.right, rect, q);
    }

    public Point2D nearest(Point2D p) {
        assertPoint(p);
        if (isEmpty()) return null;
        Point2D nst = root.p;
        boolean vertical;
        return nearestSearch(root, p, nst, true);
    }             // a nearest neighbor in the set to point p; null if the set is empty

    private Point2D nearestSearch(KdNode kn, Point2D p, Point2D nst, boolean ver) {
        if (kn == null) return null;
        if (kn.p.equals(p)) return kn.p;
        //double minDis = p.distanceSquaredTo(kn.p);
        if (kn.p.distanceSquaredTo(p) < nst.distanceSquaredTo(p)) nst = kn.p;

        int cmp;
        if (ver) cmp = Point2D.X_ORDER.compare(kn.p, p);
        else cmp = Point2D.Y_ORDER.compare(kn.p, p);

        if (cmp < 0) {
            nst = nearestSearch(kn.left, nst, p, !ver);
            if (kn.left != null && nst.distanceSquaredTo(p) > kn.rec.distanceSquaredTo(p))
                nst = nearestSearch(kn.right, nst, p, !ver);
        } else {
            nst = nearestSearch(kn.right, p, nst, !ver);
            if (kn.right != null && nst.distanceSquaredTo(p) > kn.rec.distanceSquaredTo(p))
                nst = nearestSearch(kn.left, p, nst, !ver);
        }
        return nst;
    }

    public static void main(String[] args) {

    }                  // unit testing of the methods (optional)
}
