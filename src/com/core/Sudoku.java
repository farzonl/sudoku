package com.core;

import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.Arrays; 

public class Sudoku {
  // Rules:
  // 1. Fill 9 x 9 matrix with numbers 1 to 9.
  // 2. Each 3 x 3 sub matrix contains all digits no duplicatess.
  // 3. Each row contains all digits no duplicatess.
  // 4. Each column contains all digits no duplicatess.
  // Plan of attack: write a boolean check for
  // rules 2-4 each as their own helper functions.
  // write a recursive solve function to allow for easier back tracking
  // for brute force search

  private int[][] matrix;
  private static final int DIM = 9;
  private static final CELL END = new CELL(-1, -1);

  public Sudoku() {
    matrix = new int[DIM][DIM];

    IntStream.range(0, DIM * DIM).forEach(n -> {
      int i = n / DIM;
      int j = n % DIM;
      matrix[i][j] = 0;
    });
  }

  public Sudoku(int[][] m) {
    // ideally we would have liked to have just done:
    // this.matrix = m; but this would allow outside class
    // to change our internal representation, safer to copy instead.
    // Note: intentionally not doing a clone of rows.
    matrix = new int[DIM][DIM];
    IntStream.range(0, DIM * DIM).forEach(n -> {
      int i = n / DIM;
      int j = n % DIM;
      matrix[i][j] = m[i][j];
    });
  }

  private boolean isRowSafe(int r, int n) {
    return IntStream.range(0,DIM).noneMatch(c -> matrix[r][c] == n);
  }

  private boolean isColSafe(int c, int n) {
    return IntStream.range(0,DIM).noneMatch(r -> matrix[r][c] == n);
  }

  private boolean isSubMatSafe(int boxRow, int boxCol, int n) {
    return IntStream.range(0, 9).noneMatch(i -> {
            int r = i / 3;
            int c = i % 3;
            return matrix[r + boxRow][c + boxCol] == n;
        });
  }

  private int getBoxIndex(int base) {
    return getBoxIndex(base, 3);
  }

  private int getBoxIndex(int base, int modBy) {
    return base - (base % modBy);
  }

  public boolean isSafe(int r, int c, int n) {
    return isRowSafe(r, n) && isColSafe(c, n) && isSubMatSafe(getBoxIndex(r), getBoxIndex(c), n);
  }

  public int[][] getMat() {
    // Note: java has no return readonly so we won't return matrix;
    // b\c that would expose internal representation. Instead we will
    // copy each row into a new matrix, and return that.
    return Arrays.stream(matrix).map(int[]::clone).toArray(int[][]::new);
  }

  public CELL getOpenCell() {
    for (int i = 0; i < DIM; i++) {
      for (int j = 0; j < DIM; j++) {
        if (matrix[i][j] == 0) {
          return new CELL(i, j);
        }
      }
    }
    return END;
  }

  private void add(CELL open, int n) {
    matrix[open.row][open.col] = n;
  }

  public boolean solve() {
    CELL openCELL = getOpenCell();
    if (openCELL == END) {
      return true;
    }
    for (int n = 1; n < 10; n++) {
      if (isSafe(openCELL.row, openCELL.col, n)) {
        add(openCELL, n);
        if (solve()) {
          return true;
        }
        add(openCELL, 0); // reset
      }
    }

    return false;
  }

  public void print() {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }
}
