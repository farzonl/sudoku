package com.run;

import com.core.sudoku.SudokuSolver;
import com.io.csv.CsvReader;

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
    if (args.length < 1) {
      System.out.println("usage: <app> <filename>.csv");
      return;
    }
    // note: the csv reader is barebones
    SudokuSolver s = new SudokuSolver(new CsvReader(args[0]).read());
    s.solve();
    s.print();
  }
}
