package com.io.csv;

import com.core.Helper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvReader {
  private String filePath;
  private String delimiter;
  private int[][] matrix;

  /**
   * the constructor for the CSV Reader.
   *
   * @param filePath - the directory to the csv file we want to read in.
   */
  public CsvReader(String filePath, String delimiter) {
    this.filePath = filePath;
    this.delimiter = delimiter;
    this.matrix = null;
  }

  public CsvReader(String filePath) {
    this(filePath, ",");
  }

  /**
   * Getter for the read in matrix from the csv file. We persist this incase it is needed for use
   * again, without having to read the csv all over again.
   *
   * @return A clone of the internal matrix.
   */
  public int[][] getMatrix() {
    return Helper.cloneMatrix(matrix);
  }

  /**
   * A streamable csv file read function that converts the file into a 2d matrix.
   *
   * @return returns a matrix of the read in 2d array.
   */
  public int[][] read() {
    List<List<Integer>> mat = new ArrayList<List<Integer>>();
    try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
      stream.forEach(
          line -> {
            mat.add(
                Arrays.stream(line.split(",")).map(Integer::valueOf).collect(Collectors.toList()));
          });
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.matrix =
        mat.stream().map(u -> u.stream().mapToInt(i -> i).toArray()).toArray(int[][]::new);

    return getMatrix();
  }
}
