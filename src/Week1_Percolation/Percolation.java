/* *****************************************************************************
 *  Name: Ekie
 *  Date: 05/10/2020
 *  Description: Coursera Course Algorithm
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid; //to check if the site is open or not
    private WeightedQuickUnionUF uf;
    private int n;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        this.n = n;
        if (n <= 0) {
            throw new IllegalArgumentException(n + " must be larger than 0!");
        }
        else {
            uf = new WeightedQuickUnionUF(n * n + 1);
            grid = new boolean[n][n];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    grid[i - 1][j - 1] = false;
                }
            }

        }
    }

    public boolean isInGrid(int row, int col) {
        if (row > 0 && row < grid.length && col > 0 && col < grid.length) return true;
        else return false;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int n = grid.length;
        int index = (row - 1) * n + col - 1;
        if (!isOpen(row, col)) {
            if (isInGrid(row, col - 1) && !isOpen(row, col - 1)) uf.union(index, index - 1);
            if (isInGrid(row, col + 1) && !isOpen(row, col + 1)) uf.union(index, index + 1);
            if (isInGrid(row - 1, col) && !isOpen(row - 1, col)) uf.union(index, index - n);
            if (isInGrid(row + 1, col) && !isOpen(row + 1, col)) uf.union(index, index + n);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > grid.length || col < 1 || col > grid.length)
            throw new IllegalArgumentException("index out of range");
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        boolean full = false;
        if (isOpen(row, col)) {
            for (int i = 1; i <= grid.length; i++) {
                if (isOpen(1, i)) {
                    full = uf.connected(i, (row - 1) * grid.length + col - 1);
                }
            }
        }
        return full;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int site = 0;
        for (int i = 1; i <= grid.length; i++) {
            for (int j = 1; j <= grid.length; j++) {
                if (grid[i][j]) site++;
            }
        }
        return site;
    }

    ;

    // does the system percolate?
    public boolean percolates() {
        boolean perc = false;
        n = grid.length;
        for (int i = 1; i <= n; i++) {
            perc = isFull(n, i);
        }
        return perc;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(4);
        System.out.println(percolation.percolates());
        percolation.open(1, 1);
        percolation.open(2, 1);
        System.out.println(percolation.percolates());
        percolation.open(3, 1);
        percolation.open(3, 2);
        System.out.println(percolation.percolates());
        System.out.println(percolation.isFull(3, 2));
        percolation.open(4, 2);
        System.out.println(percolation.percolates());
    }

    ;
}
