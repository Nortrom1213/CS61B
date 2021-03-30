package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int N;
    private boolean[][] status;

    private int num;
    private int top;
    private int bot;
    private WeightedQuickUnionUF set;
    private WeightedQuickUnionUF helperset;

    private int index(int row, int col) {
        return row * N + col;
    }

    public Percolation(int N) {

        if (N <= 0) {
            throw new IllegalArgumentException();
        }

        this.N = N;
        status = new boolean[N][N];
        num = 0;
        set = new WeightedQuickUnionUF(N * N + 2);
        helperset = new WeightedQuickUnionUF(N * N + 2);
        top = N * N;
        bot = N * N + 1;

    }

    private void validate(int p) {
        if (p < 0 || p >= N) {
            throw new IndexOutOfBoundsException();
        }
    }

    public void open(int row, int col) {
        validate(row);
        validate(col);
        if (!isOpen(row, col)) {
            num += 1;
            dig(row, col);
            status[row][col] = true;
        }
    }

    private void dig(int row, int col) {
        // helperset 是全部连上的， 用来检验是否贯通
        // set 是不算bot的

        int index = index(row, col);

        if (row == 0) {
            helperset.union(top, index);
            set.union(top, index);
        }
        if (row == N - 1) {
            helperset.union(bot, index);
        }

        if (row != 0 && isOpen(row - 1, col)) {
            set.union(index(row - 1, col), index);
            helperset.union(index(row - 1, col), index);
        }
        if (col != 0 && isOpen(row, col - 1)) {
            set.union(index(row, col - 1), index);
            helperset.union(index(row, col - 1), index);
        }
        if ((row != N - 1) && isOpen(row + 1, col)) {
            set.union(index(row + 1, col), index);
            helperset.union(index(row + 1, col), index);
        }
        if ((col != N - 1) && isOpen(row, col + 1)) {
            set.union(index(row, col + 1), index);
            helperset.union(index(row, col + 1), index);
        }
    }

    public boolean isOpen(int row, int col) {
        validate(row);
        validate(col);
        return status[row][col];
    }

    public boolean isFull(int row, int col) {
        validate(row);
        validate(col);
        return set.connected(top, index(row, col)) && isOpen(row, col);
    }

    public int numberOfOpenSites() {
        return num;
    }

    public boolean percolates() {
        return helperset.connected(top, bot);
    }

    public static void main(String[] args) {
    }
}
