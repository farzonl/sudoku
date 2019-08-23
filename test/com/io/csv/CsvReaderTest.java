package com.io.csv;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import com.core.sudoku.SudokuSolver;
import com.run.Main;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import org.junit.Test;

public class CsvReaderTest {
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

  @Test
  public void testcsvFile1() {
    CsvReader reader = new CsvReader("resources/test1.csv");
    reader.read();
    int[][] readMat = reader.getMatrix();
    for (int i = 0; i < hard.length; i++) {
      assertArrayEquals(hard[i], readMat[i]);
    }
    SudokuSolver s = new SudokuSolver(readMat);
    s.solve();
    int[][] ansMat = s.getMat();
    for (int i = 0; i < hardSln.length; i++) {
      assertArrayEquals(hardSln[i], ansMat[i]);
    }
  }

  @Test
  public void testMain() throws IOException, UnsupportedEncodingException {
    String[] args = {"resources/test1.csv"};
    final PrintStream original = System.out;
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(stream, true, "UTF-8");
    System.setOut(ps);
    Main.main(args);
    final String output = stream.toString("UTF-8");

    ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
    PrintStream ps2 = new PrintStream(stream2, true, "UTF-8");
    System.setOut(ps2);
    CsvReader reader = new CsvReader("resources/test1.csv");
    SudokuSolver s = new SudokuSolver(reader.read());
    s.solve();
    s.print();
    final String output2 = stream2.toString("UTF-8");
    System.setOut(original);
    assertEquals(output2, output);
  }

  @Test
  public void testMainFailure() throws UnsupportedEncodingException {
    String[] args = {};
    final PrintStream original = System.out;
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(stream, true, "UTF-8");
    System.setOut(ps);
    Main.main(args);
    final String output = stream.toString("UTF-8");
    System.setOut(original);
    assertEquals("usage: <app> <filename>.csv\n", output);
  }
}
