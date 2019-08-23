package com.run;

import com.core.Sudoku;

/**
 * A class that runs the sudoku solver.
 *
 * @author Farzon Lotfi
 * @version 1.0
 */
public class Main {

  /**
   * The main function, used to solve a given sudoku puzzle.
   *
   * @param args input csv file that we parse into a sudoku puzzle matrix.
   */
  public static void main(String[] args) {
    int[][] g = {
      {0, 0, 2, 0, 0, 0, 0, 4, 1},
      {0, 0, 0, 0, 8, 2, 0, 7, 0},
      {0, 0, 0, 0, 4, 0, 0, 0, 9},
      {2, 0, 0, 0, 7, 9, 3, 0, 0},
      {0, 1, 0, 0, 0, 0, 0, 8, 0},
      {0, 0, 6, 8, 1, 0, 0, 0, 4},
      {1, 0, 0, 0, 9, 0, 0, 0, 0},
      {0, 6, 0, 4, 3, 0, 0, 0, 0},
      {8, 5, 0, 0, 0, 0, 4, 0, 0}
    };
    Sudoku s = new Sudoku(g);
    s.solve();
    s.print();
  }
}
