package com.core.sudoku;

import com.core.Cell;
import com.core.Helper;
import java.util.Arrays;

public class PuzzleGenerator extends SudokuSolver {
  // use the SudokuSolver to generate unique puzzles
  //
  private int[] gridPos;
  private int[] guesses;
  private int[][] slnCpy;

  /**
   * constructor of the PuzzleGenerator. sets up random shuffles and initializes the Sudoku matrix.
   */
  public PuzzleGenerator() {
    super();
    genGridPos();
    genGuesses();
    cloneSln();
    genPuzzle();
  }

  private void genGridPos() {
    gridPos = new int[DIM * DIM];
    Arrays.setAll(gridPos, i -> i);
    Helper.shuffle(gridPos);
  }

  public int[] getGridPos() {
    return gridPos.clone();
  }

  public int[] getGuesses() {
    return guesses.clone();
  }

  private void genGuesses() {
    guesses = new int[DIM];
    Arrays.setAll(guesses, i -> i + 1);
    Helper.shuffle(guesses);
  }

  private void cloneSln() {
    this.solve();
    slnCpy = this.getMat();
  }

  public int[][] puzzleSln() {
    return Helper.cloneMatrix(slnCpy);
  }

  /** A function that checks if we can unassigned a cell and still have a unique Sudoku matrix. */
  private void genPuzzle() {
    Arrays.stream(gridPos)
        .forEach(
            n -> {
              int i = n / DIM;
              int j = n % DIM;
              int temp = matrix[i][j];
              matrix[i][j] = 0;
              if (!isGridUnique()) {
                matrix[i][j] = temp;
              }
            });
  }

  private boolean isGridUnique() {
    return gridUniqueness(0) == 1;
  }

  private int gridUniqueness(int count) {
    Cell openCell = getOpenCell();
    if (openCell == END) {
      count++;
      return count;
    }
    for (int i = 0; i < DIM; i++) {
      if (count > 1) {
        break;
      }
      int g = guesses[i];
      if (isSafe(openCell.row, openCell.col, g)) {
        add(openCell, g);
        count = gridUniqueness(count);
      }
      add(openCell, 0); // reset
    }
    return count;
  }

  /**
   * A recursive function that solves Sudoku.
   *
   * @return will return true when solution is found.
   */
  @Override
  public boolean solve() {
    Cell openCell = getOpenCell();
    if (openCell == END) {
      return true;
    }
    for (int n = 0; n < DIM; n++) {
      if (isSafe(openCell.row, openCell.col, guesses[n])) {
        add(openCell, guesses[n]);
        if (solve()) {
          return true;
        }
        add(openCell, 0); // reset
      }
    }

    return false;
  }
}
