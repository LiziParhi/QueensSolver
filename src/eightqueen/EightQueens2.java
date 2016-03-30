/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eightqueen;
import java.util.*;

/**
 *
 * @author lizilina
 */

class Board {

    private boolean[][] board;
    int size;

    public Board(int size) {
        this.size = size;
        this.board = new boolean[size][size];
    }

    public Board(Board oldBoard) {
        this.size = oldBoard.size;
        this.board = new boolean[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                this.board[i][j] = oldBoard.board[i][j];
            }
        }
    }

    public void setQueenPresent(int x, int y, boolean value) {
        board[x][y] = value;
    }

    public boolean QueenPresent(int x, int y) {
        return board[x][y];
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("\n");
        for (int i = 0; i < size; i++) {
            result.append("|");
            for (int j = 0; j < size; j++) {
                result.append((board[i][j] ? "Q" : " ") + "|");
            }
            result.append("\n");
        }
        result.append("\n");
        return result.toString();
    }

}

class BooleanBoard {

    private boolean[][] board;
    int size;

    public BooleanBoard(int size) {
        this.size = size;
        board = new boolean[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                this.board[i][j] = true;
            }
        }
    }

    public BooleanBoard(BooleanBoard oldBoard) {
        this.size = oldBoard.size;
        this.board = new boolean[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                this.board[i][j] = oldBoard.board[i][j];
            }
        }
    }

    public void setPossible(int x, int y, boolean value) {
        board[x][y] = value;
    }

    public boolean getPossible(int x, int y) {
        return board[x][y];
    }

}
class QueensProblem {

    private int size;

    private List<Board> solutions;

    public QueensProblem(int size) {
        this.size = size;
        this.solutions = new LinkedList<Board>();
        solve(size);
    }

    public void solve(int size) {
        Board board = new Board(size);
        BooleanBoard booleanboard = new BooleanBoard(size);
        solve(board, booleanboard, 0, size);
    }

    public List<Board> findAllPositions() {
        return solutions;
    }

    private void solve(Board board, BooleanBoard booleanboard,
            int rownum, int size) {
        if (rownum == size) {
            solutions.add(board);
            return;
        }
        for (int colnum = 0; colnum < size; colnum++) {
            if (booleanboard.getPossible(rownum, colnum)) {
                Board newBoard = new Board(board);
                newBoard.setQueenPresent(rownum, colnum, true);
                BooleanBoard newBooleanBoard = new BooleanBoard(booleanboard);
                newBooleanBoard.setPossible(rownum, colnum, false);
                updateBoard(rownum, colnum, newBooleanBoard, size);
                solve(newBoard, newBooleanBoard, rownum + 1, size);
            }
        }
    }

    private void updateBoard(int row, int col, BooleanBoard booleanboard, int size) {
        // update the row
        for (int j = 0; j < size; j++) {
            booleanboard.setPossible(row, j, false);
        }
        // update the colnum
        for (int j = 0; j < size; j++) {
            booleanboard.setPossible(j, col, false);
        }
        // update the diagonals
        int r = row;
        int c = col;
        while (r >= 0 && c >= 0) {
            booleanboard.setPossible(r, c, false);
            r--;
            c--;
        }
        r = row;
        c = col;
        while (r < 8 && c < 8) {
            booleanboard.setPossible(r, c, false);
            r++;
            c++;
        }
        r = row;
        c = col;
        while (r < 8 && c >= 0) {
            booleanboard.setPossible(r, c, false);
            r++;
            c--;
        }
        r = row;
        c = col;
        while (r >= 0 && c < 8) {
            booleanboard.setPossible(r, c, false);
            r--;
            c++;
        }
    }


}


public class EightQueens2 {
    

    public static void main(String[] args) {
        List<Board> solutions = new QueensProblem(8).findAllPositions();
        for(Board board : solutions) {
            System.out.println(board.toString());
        }
    }

}

    

