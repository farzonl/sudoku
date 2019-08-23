package com.sudoku;

import static org.junit.Assert.assertArrayEquals;

import com.core.Sudoku;
import org.junit.Test;

public class SudokuTest {
  int[][] z0 = {
    {0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0, 0, 0}
  };

  int[][] z0Sln = {
    {1, 2, 3, 4, 5, 6, 7, 8, 9},
    {4, 5, 6, 7, 8, 9, 1, 2, 3},
    {7, 8, 9, 1, 2, 3, 4, 5, 6},
    {2, 1, 4, 3, 6, 5, 8, 9, 7},
    {3, 6, 5, 8, 9, 7, 2, 1, 4},
    {8, 9, 7, 2, 1, 4, 3, 6, 5},
    {5, 3, 1, 6, 4, 2, 9, 7, 8},
    {6, 4, 2, 9, 7, 8, 5, 3, 1},
    {9, 7, 8, 5, 3, 1, 6, 4, 2}
  };

  int[][] easy = {
    {0, 1, 3, 8, 0, 0, 4, 0, 5},
    {0, 2, 4, 6, 0, 5, 0, 0, 0},
    {0, 8, 7, 0, 0, 0, 9, 3, 0},
    {4, 9, 0, 3, 0, 6, 0, 0, 0},
    {0, 0, 1, 0, 0, 0, 5, 0, 0},
    {0, 0, 0, 7, 0, 1, 0, 9, 3},
    {0, 6, 9, 0, 0, 0, 7, 4, 0},
    {0, 0, 0, 2, 0, 7, 6, 8, 0},
    {1, 0, 2, 0, 0, 8, 3, 5, 0}
  };

  int[][] hard = {
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

  @Test
  public void testDefaultSudokuConstructor() {
    Sudoku suDefault = new Sudoku();
    int[][] ansMat = suDefault.getMat();
    for (int i = 0; i < ansMat.length; i++) {
      assertArrayEquals(ansMat[i], z0[i]);
    }
    suDefault.solve();
    ansMat = suDefault.getMat();
    for (int i = 0; i < z0Sln.length; i++) {
      assertArrayEquals(z0Sln[i], ansMat[i]);
    }
  }

  @Test
  public void testZeroArray() {
    Sudoku s = new Sudoku(z0);
    s.solve();
    int[][] ansMat = s.getMat();
    for (int i = 0; i < z0Sln.length; i++) {
      assertArrayEquals(z0Sln[i], ansMat[i]);
    }
  }

  @Test
  public void testEasyBoard() {
    int[][] easySln = {
      {6, 1, 3, 8, 7, 9, 4, 2, 5},
      {9, 2, 4, 6, 3, 5, 1, 7, 8},
      {5, 8, 7, 1, 2, 4, 9, 3, 6},
      {4, 9, 8, 3, 5, 6, 2, 1, 7},
      {7, 3, 1, 9, 8, 2, 5, 6, 4},
      {2, 5, 6, 7, 4, 1, 8, 9, 3},
      {8, 6, 9, 5, 1, 3, 7, 4, 2},
      {3, 4, 5, 2, 9, 7, 6, 8, 1},
      {1, 7, 2, 4, 6, 8, 3, 5, 9}
    };
    Sudoku s = new Sudoku(easy);
    s.solve();
    int[][] ansMat = s.getMat();
    for (int i = 0; i < easySln.length; i++) {
      assertArrayEquals(easySln[i], ansMat[i]);
    }
  }

  @Test
  public void testHardBoard() {
    int[][] hardSln = {
      {6, 3, 2, 9, 5, 7, 8, 4, 1},
      {4, 9, 1, 6, 8, 2, 5, 7, 3},
      {7, 8, 5, 3, 4, 1, 2, 6, 9},
      {2, 4, 8, 5, 7, 9, 3, 1, 6},
      {3, 1, 9, 2, 6, 4, 7, 8, 5},
      {5, 7, 6, 8, 1, 3, 9, 2, 4},
      {1, 2, 4, 7, 9, 5, 6, 3, 8},
      {9, 6, 7, 4, 3, 8, 1, 5, 2},
      {8, 5, 3, 1, 2, 6, 4, 9, 7}
    };
    Sudoku s = new Sudoku(hard);
    s.solve();
    int[][] ansMat = s.getMat();
    for (int i = 0; i < hardSln.length; i++) {
      assertArrayEquals(hardSln[i], ansMat[i]);
    }
  }
}
