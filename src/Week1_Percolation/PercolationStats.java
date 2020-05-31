/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int n;
    private double x;
    private double s;
    private double low;
    private double high;
    private double[] threshold;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        this.n = n;
        if(n<1) {
            throw new IllegalArgumentException();
        }
        else {
            threshold = new double[trials];
            for (int i = 0; i < trials; i++) {
                Percolation p = new Percolation(n);
                while (!p.percolates()) {
                    int row = StdRandom.uniform(1, n + 1);
                    int col = StdRandom.uniform(1, n + 1);
                    if (!p.isOpen(row, col)) {
                        p.open(row, col);
                    }
                }
                threshold[i]=(double)p.numberOfOpenSites()/n/n;
            }
        }
    };

    // sample mean of percolation threshold
    public double mean(){
        x=StdStats.mean(threshold);
        return x;}

    // sample standard deviation of percolation threshold
    public double stddev(){
        s=StdStats.stddev(threshold);
        return s;}

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        low=x-1.96*s/Math.sqrt((double)threshold.length);
        return low;}

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        high=x+1.96*s/Math.sqrt((double)threshold.length);
        return high;}

    // test client (see below)
    public static void main(String[] args){
        PercolationStats ps = new PercolationStats(5,5);
        System.out.println("mean=" + ps.mean());
        System.out.println("stddev=" + ps.stddev());
        System.out.println("95%% confidence Interval=" + ps.confidenceLo() + " "
                + ps.confidenceHi());
    };

}
