package com.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Helper {

  /**
   * Helper function that clones a 2d array and returns it.
   *
   * @param matrix - the 2d array we want to clone.
   */
  public static int[][] cloneMatrix(int[][] matrix) {
    return Arrays.stream(matrix).map(int[]::clone).toArray(int[][]::new);
  }

  /**
   * A function used to shuffle an array randomly using a swap between The current index and a
   * random index.
   *
   * @param array the array we will shuffle.
   */
  public static void shuffle(int[] array) {
    Random rndGen = new Random();
    for (int i = 0; i < array.length; i++) {
      int randPos = rndGen.nextInt(array.length);
      swap(array, i, randPos);
    }
  }

  /**
   * A simple swap function that swaps elements in an array by indicies.
   *
   * @param array The array we are looking to swap values between.
   * @param i The first index we will swap.
   * @param j The second index we will swap.
   */
  public static void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  /**
   * A function that converts an int array into a Integer List.
   *
   * @param array The array we will convert into a list.
   * @return A List version of the input array.
   */
  public static List<Integer> arrayToList(int[] array) {
    return Arrays.stream(array).boxed().collect(Collectors.toList());
  }

  // public static boolean matrixCotains(int[][] mat, int n) {
  //  return IntStream.range(0, mat.length * mat[0].length)
  //      .anyMatch(
  //          i -> {
  //            int r = i / mat.length;
  //            int c = i % mat.length;
  //            return mat[r][c] == n;
  //          });
  // }

  /**
   * A generic function that counts frequency of an element in a list.
   *
   * @param list The collection we will iterate over to find frequencies.
   * @param n The element we will search for to increment our counter.
   */
  public static <T> int countElements(List<T> list, T n) {
    return Collections.frequency(list, n);
  }

  /**
   * A function that counts frequency of an element in a matrix.
   *
   * @param mat The 2d array we will iterate over to find frequencies.
   * @param n The element we will search for to increment our counter.
   */
  public static int countMatElements(int[][] mat, int n) {
    return Arrays.stream(mat)
        .map(arr -> countElements(arrayToList(arr), n))
        .reduce(0, Integer::sum);
  }
}