package com.core;

/**
 * A class used as a Tuple for row and column indices.
 *
 * @author Farzon Lotfi
 * @version 1.0
 */
public class Cell {
  public int row;
  public int col;

  /**
   * constructor for row and column indices.
   *
   * @param r row index.
   * @param c column index.
   */
  public Cell(int r, int c) {
    row = r;
    col = c;
  }
}
