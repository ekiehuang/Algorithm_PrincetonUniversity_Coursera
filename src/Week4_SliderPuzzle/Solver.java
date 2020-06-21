import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private boolean isSolvable;
    private Stack<Board> solution = new Stack<>();
    private int step;

    private class SearchNode implements Comparable<SearchNode> {

        Board board;
        int move;
        SearchNode prev;
        int manh;

        SearchNode(Board current, int move, SearchNode prev) {
            board = current;
            this.move = move;
            this.prev = prev;
            manh = current.manhattan();
        } // avoid repeating call of manhattan();

        private int mhPri() {
            return move + manh;
        }

        private int cost1() {
            return move + manh;
        }

        private int cost2() {
            return manh;
        }

        public int compareTo(SearchNode that) {//first determine the priority by the moves, then if the move is the same, determine it by manhanton steps
            /*if (this.board.equals(that.board)) return 0;
            if (this.mhPri() < that.mhPri()) return -1;
            else return 1;*/
            int diff = this.cost1() - that.cost1();
            if (diff == 0)
                return this.cost2() - that.cost2();//among neighbors, moves are the same, so determine it by manhanton move;
            else return diff;//determine by the move
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        MinPQ<SearchNode> pq = new MinPQ<>();
        SearchNode current;
        if (initial == null) throw new IllegalArgumentException("No board");
        pq.insert(new SearchNode(initial, 0, null));
        pq.insert(new SearchNode(initial.twin(), 0, null));
        current = pq.delMin();

        while (!current.board.isGoal()) {
            for (Board b : current.board.neighbors()) {
                if (current.prev == null) {
                    //current = new SearchNode(b, current.move + 1, current);
                    pq.insert(new SearchNode(b, current.move + 1, current));
                } else if (!b.equals(current.prev.board))
                    pq.insert(new SearchNode(b, current.move + 1, current));
                //current = new SearchNode(b, current.move + 1, current);
                //pq.insert(current);
            }
            current = pq.delMin();
        }
        step = current.move;
        if (current.board.isGoal()) {
            //isSolvable = true;
            while (current != null) {
                solution.push(current.board);
                current = current.prev;
            }
            if (solution.pop().equals(initial)) {
                isSolvable = true;
            }
            solution.push(initial);
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return isSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (isSolvable())
            return step;
        else return -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;
        else return solution;
    }

    // test client (see below)
    public static void main(String[] args) {
        /*In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();*/
        int[][] tiles = new int[][]{{1, 0, 6}, {2, 4, 8}, {3, 5, 7}};
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}
