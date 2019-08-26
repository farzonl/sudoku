package com.core.sudoku;

import com.core.Cell;
import com.core.Helper;
import java.util.stream.IntStream;

public class SudokuSolver {
  // Rules:
  // 1. Fill 9 x 9 matrix with numbers 1 to 9.
  // 2. Each 3 x 3 sub matrix contains all digits no duplicates.
  // 3. Each row contains all digits no duplicates.
  // 4. Each column contains all digits no duplicates.
  // Plan of attack: write a boolean check for
  // rules 2-4 each as their own helper functions.
  // write a recursive solve function to allow for easier back tracking
  // for brute force search

  protected int[][] matrix;
  protected static final int DIM = 9;
  protected static final Cell END = new Cell(-1, -1);

  /** default constructor initializes matrix to zero. */
  public SudokuSolver() {
    matrix = new int[DIM][DIM];

    IntStream.range(0, DIM * DIM)
        .forEach(
            n -> {
              int i = n / DIM;
              int j = n % DIM;
              matrix[i][j] = 0;
            });
  }

  /**
   * constructor that sets matrix to the input.
   *
   * @param m - input matrix compiled to internal representation.
   */
  public SudokuSolver(int[][] m) {
    // ideally we would have liked to have just done:
    // this.matrix = m; but this would allow outside class
    // to change our internal representation, safer to copy instead.
    // Note: intentionally not doing a clone of rows.
    matrix = new int[DIM][DIM];
    IntStream.range(0, DIM * DIM)
        .forEach(
            n -> {
              int i = n / DIM;
              int j = n % DIM;
              matrix[i][j] = m[i][j];
            });
  }

  private boolean isRowSafe(int r, int n) {
    return IntStream.range(0, DIM).noneMatch(c -> matrix[r][c] == n);
  }

  private boolean isColSafe(int c, int n) {
    return IntStream.range(0, DIM).noneMatch(r -> matrix[r][c] == n);
  }

  private boolean isSubMatSafe(int boxRow, int boxCol, int n) {
    return IntStream.range(0, DIM)
        .noneMatch(
            i -> {
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

  /**
   * isSafe checks rules 2-4 to make sure a row, column, and matrix is safe for the addition of the
   * input number.
   *
   * @param r - current row we want to check.
   * @param c - current column we want to check.
   * @param n - current number we want to check.
   * @return returns true if safe to write to cell, false otherwise.
   */
  public boolean isSafe(int r, int c, int n) {
    return isRowSafe(r, n) && isColSafe(c, n) && isSubMatSafe(getBoxIndex(r), getBoxIndex(c), n);
  }

  /**
   * A getter method for the internal matrix.
   *
   * @return a copy of the internal matrix.
   */
  public int[][] getMat() {
    // Note: java has no return read-only so we won't return matrix;
    // b\c that would expose internal representation. Instead we will
    // copy each row into a new matrix, and return that.
    return Helper.cloneMatrix(matrix);
  }

  protected Cell getOpenCell() {
    for (int i = 0; i < DIM; i++) {
      for (int j = 0; j < DIM; j++) {
        if (matrix[i][j] == 0) {
          return new Cell(i, j);
        }
      }
    }
    return END;
  }

  protected void add(Cell open, int n) {
    matrix[open.row][open.col] = n;
  }

  /**
   * A recursive function that solves Sudoku.
   *
   * @return will return true when solution is found.
   */
  public boolean solve() {
    Cell openCell = getOpenCell();
    if (openCell == END) {
      return true;
    }
    for (int n = 1; n < 10; n++) {
      if (isSafe(openCell.row, openCell.col, n)) {
        add(openCell, n);
        if (solve()) {
          return true;
        }
        add(openCell, 0); // reset
      }
    }

    return false;
  }

  /** A diagnostics function used to visually evaluating a matrix by printing to the console. */
  public void print() {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }
}
