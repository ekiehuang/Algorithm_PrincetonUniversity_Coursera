import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class Board {
    private final int[][] tl;
    private int n;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        double a = 1;
        double b = 2;
        Double x = new Double(a);
        Double y = x;
        n = tiles.length;
        if (n < 2 || n >= 128) {
            throw new IllegalArgumentException("The lines must be between 2 and 128");
        }
        this.tl = new int[n][n];
        for (int row = 1; row <= n; row++) {
            System.arraycopy(tiles[row - 1], 0, this.tl[row - 1], 0, n);
        }

    }

    private int[][] cpTile(int[][] tiles) {
        int[][] copy = new int[n][n];
        for (int row = 1; row <= n; row++) {
            System.arraycopy(tiles[row - 1], 0, copy[row - 1], 0, n);
        }
        return copy;
    }

    // string representation of this board
    public String toString() {
        String s = n + "\n";
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                s = s + " " + tl[row - 1][col - 1];
                if (col == n) s = s + "\n";
            }
        }
        return s;
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        int hamming = 0;
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                if ((tl[row - 1][col - 1] != ((row - 1) * n + col)) && tl[row - 1][col - 1] != 0) hamming++;
            }
        }
        return hamming;
    }


    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int r, c;
        int manhattan = 0;
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                if ((tl[row - 1][col - 1] != ((row - 1) * n + col)) && tl[row - 1][col - 1] != 0) {
                    if (tl[row - 1][col - 1] % n == 0) {
                        r = tl[row - 1][col - 1] / n;
                        c = n;
                    } else {
                        r = tl[row - 1][col - 1] / n + 1;
                        c = tl[row - 1][col - 1] % n;
                    }
                    manhattan = manhattan + Math.abs(col - c) + Math.abs(row - r);
                }
            }
        }
        return manhattan;
    }

    private int moves(int[][] block) {
        int r, c;
        int l = block.length;
        int move = 0;
        for (int row = 1; row <= l; row++) {
            for (int col = 1; col <= l; col++) {
                if ((block[row - 1][col - 1] != ((row - 1) * l + col)) && block[row - 1][col - 1] != 0) {
                    if (block[row - 1][col - 1] % l == 0) {
                        r = block[row - 1][col - 1] / l;
                        c = l;
                    } else {
                        r = block[row - 1][col - 1] / l + 1;
                        c = block[row - 1][col - 1] % l;
                    }
                    move = move + Math.abs(col - c) + Math.abs(row - r);
                }
            }
        }
        return move;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    private boolean isInGrid(int row, int col) {
        if (row >= 1 && row <= n && col >= 1 && col <= n) return true;
        else return false;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;

        Board that = (Board) y;
        if (that.n != n) return false;
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                if (tl[row - 1][col - 1] != that.tl[row - 1][col - 1])
                    return false;
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        int row = 1;
        int col = 1;
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++) {
                if (tl[i - 1][j - 1] == 0) {
                    row = i;
                    col = j;
                    break;
                }

            }
        Queue<Board> neighbors = new Queue<Board>();

        if (isInGrid(row, col - 1)) {
            neighbors.enqueue(neighbor(row, col, row, col - 1));
        }
        if (isInGrid(row, col + 1)) {
            neighbors.enqueue(neighbor(row, col, row, col + 1));
        }
        if (isInGrid(row - 1, col)) {
            neighbors.enqueue(neighbor(row, col, row - 1, col));
        }
        if (isInGrid(row + 1, col)) {
            neighbors.enqueue(neighbor(row, col, row + 1, col));
        }
        return neighbors;

    }

    private Board neighbor(int row, int col, int row1, int col1) {
        Board neighbor = new Board(swap(row, col, row1, col1));
        //int hmPriority = neighbor.hamming() +;
        //int mhPriority = neighbor.manhattan();
        return neighbor;
    }

    private int[][] swap(int row, int col, int row1, int col1) {
        int[][] cp = cpTile(tl);
        int temp = cp[row - 1][col - 1];
        cp[row - 1][col - 1] = cp[row1 - 1][col1 - 1];
        cp[row1 - 1][col1 - 1] = temp;
        return cp;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        if (tl[0][0] != 0 && tl[0][1] != 0)
            return new Board(swap(1, 1, 1, 2));
        else return new Board(swap(2, 1, 2, 2));
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] a = new int[][]{{2, 0, 8}, {3, 6, 1}, {4, 7, 5}};
        Board bd = new Board(a);
        StdOut.println(bd.toString());
        StdOut.println(bd.hamming());
        StdOut.println(bd.manhattan());
        StdOut.println(bd.dimension());
        StdOut.println(bd.isGoal());
        StdOut.println(bd.neighbors());
        StdOut.println(bd.twin());

    }


}