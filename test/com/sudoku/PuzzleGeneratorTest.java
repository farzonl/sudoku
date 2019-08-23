package com.sudoku;

import static org.junit.Assert.assertEquals;

import com.core.Helper;
import com.core.sudoku.PuzzleGenerator;
import com.core.sudoku.SudokuSolver;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class PuzzleGeneratorTest {

  @Test
  public void testRandomGridPos() {
    PuzzleGenerator pg = new PuzzleGenerator();
    int[] gridPos = pg.getGridPos();
    Set<Integer> gridPosSet = new HashSet<Integer>(Helper.arrayToList(gridPos));
    assertEquals(gridPosSet.size(), gridPos.length);

    // import java.util.Arrays;
    // Arrays.stream(gridPos).forEach(System.out::println);
    // gridPosSet.stream().forEach(System.out::println);
  }

  @Test
  public void testRandomGuessPos() {
    PuzzleGenerator pg = new PuzzleGenerator();
    int[] guessPos = pg.getGuesses();
    Set<Integer> guessPosSet = new HashSet<Integer>(Helper.arrayToList(guessPos));
    assertEquals(guessPosSet.size(), guessPos.length);

    // import java.util.Arrays;
    // Arrays.stream(gridPos).forEach(System.out::println);
    // gridPosSet.stream().forEach(System.out::println);
  }

  @Test
  public void testPuzzleToSolver() {
    PuzzleGenerator pg = new PuzzleGenerator();
    int[][] puzzle = pg.getMat();
    pg.print();
    SudokuSolver s = new SudokuSolver(puzzle);
    s.solve();
    System.out.println();
    // int[][] ansMat = s.getMat();
    s.print();
  }

  @Test
  public void testMatrixElementCount() {
    int[][] m = {{1, 2}, {0, 1}};
    assertEquals(Helper.countMatElements(m, 1), 2);
    assertEquals(Helper.countMatElements(m, 0), 1);
    assertEquals(Helper.countMatElements(m, 2), 1);

    int[][] m2 = {{1, 2}, {2, 1}};
    assertEquals(Helper.countMatElements(m2, 0), 0);
    assertEquals(Helper.countMatElements(m2, 1), 2);
    assertEquals(Helper.countMatElements(m2, 2), 2);

    int[][] m3 = {
      {1, 2, 3},
      {4, 5, 6},
      {7, 8, 0}
    };

    assertEquals(Helper.countMatElements(m3, 0), 1);

    int[][] m4 = {
      {1, 2, 0},
      {4, 5, 6},
      {7, 8, 0}
    };
    assertEquals(Helper.countMatElements(m4, 0), 2);
    assertEquals(Helper.countMatElements(m4, 2), 1);

    int[][] m5 = {
      {1, 2, 3},
      {4, 5, 6},
      {7, 8, 9}
    };
    assertEquals(Helper.countMatElements(m5, 0), 0);
    assertEquals(Helper.countMatElements(m5, 9), 1);
  }
}
