package com.run;

import com.core.Sudoku;

public class Main {

  public static void main(String[] args) {
    int g[][] = {
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
