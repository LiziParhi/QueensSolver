/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eightqueen;

/**
 *Print all possible configurations of 8 queens on 8 * 8 board
 * @author lizilina
 */
public class EightQueen {
    

    /**
     * @param args the command line arguments
     */
 static int count=0;

  public static void printboard(char[][] board) {
    // 8x8 board
      count++;
    int n = board.length;
    System.out.println("  Board number "+ count);
    for (int i = 0; i < n; i++) {
        System.out.print("|");
        for (int j = 0; j < n; j++) {
            System.out.print(board[i][j] + "|");
        }
        System.out.println();
    }
    System.out.println("");
}

  public static void updateBoard(int row, int col, boolean[][] booleanboard) {
 
   
    int n = booleanboard.length;

    // update the rownum
    for (int j = 0; j < n; j++) {
      booleanboard[row][j] = false;
    }

    // update the colnum
    for (int j = 0; j < n; j++) {
      booleanboard[j][col] = false;
    }

    // update the diagonals
    int r = row;
    int c = col;
    while (r >= 0 && c >= 0) {
      booleanboard[r][c] = false;
      r--;
      c--;
    }

    r = row;
    c = col;
    while (r < 8 && c < 8) {
      booleanboard[r][c] = false;
      r++;
      c++;
    }

    r = row;
    c = col;
    while (r < 8 && c >= 0) {
      booleanboard[r][c] = false;
      r++;
      c--;
    }

    r = row;
    c = col;
    while (r >= 0 && c < 8) {
      booleanboard[r][c] = false;
      r--;
      c++;
    }
  }

  public static char[][] createCopy(char[][] original) {
    char[][] copy = new char[original.length][];
    for (int i = 0; i < original.length; i++) {
      copy[i] = original[i].clone();
    }
    return copy;
  }

  public static boolean[][] createCopy(boolean[][] original) {
    boolean[][] copy = new boolean[original.length][];
    for (int i = 0; i < original.length; i++) {
      copy[i] = original[i].clone();
    }
    return copy;
  }

  public static void placeQueen(char[][] board, boolean[][] booleanboard, int rownum) {
    if (rownum == 8) {
      printboard(board);
      return;
    }

    int n = board.length;
    for (int colnum = 0; colnum < n; colnum++) {
      if (booleanboard[rownum][colnum]) {
        char[][] newBoard = createCopy(board);
        boolean[][] newbooleanboard = createCopy(booleanboard);
        newBoard[rownum][colnum] = 'Q';
        newbooleanboard[rownum][colnum] = false;
        updateBoard(rownum, colnum, newbooleanboard);
        placeQueen(newBoard, newbooleanboard, rownum + 1);
      }
    }
  }

  public static void placeQueen() {
    int N = 8;
    char[][] board = new char[8][8];
    boolean[][] booleanboard = new boolean[8][8];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        board[i][j] = ' ';
        booleanboard[i][j] = true;
      }
    }
    placeQueen(board, booleanboard, 0);
  }

  public static void main(String[] args){
    placeQueen();
  }

}
   
    


