package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import java.util.Random;

/**
 * Something that sorts using Quicksort with a hopefully better pivot on
 * average.
 *
 * @param <T> The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 * @author A.J. Trimble
 */
public class TrimbleAJSorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+
  /**
   * Create a sorter using a particular comparator.
   *
   * @param comparator The order in which elements in the array should be
   *                   ordered after sorting.
   */
  public TrimbleAJSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // quicksorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+
  /**
   * Sort an array in place using Quicksort.
   *
   * @param values an array to sort.
   *
   * @post The array has been sorted according to some order (often one given
   *       to the constructor).
   * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1],
   *       values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    if (values.length == 0) {
      return;
    } // if
    recursiveBody(values, 0, values.length);
  } // sort(T[])

  /**
   * Recusively sorts the sub-array within the given bounds.
   *
   * @param values an array to sort.
   * @param lb     the lower bound of the sub-array.
   * @param ub     the upper bound of the sub-array.
   *
   * @post The sub array has been sorted according to some order (often one given
   *       to the constructor).
   */
  public void recursiveBody(T[] values, int lb, int ub) {
    if ((ub - lb) <= 1) {
      return;
    } // if
    int[] sections = quicksort(values, lb, ub);
    recursiveBody(values, lb, sections[0]);
    recursiveBody(values, sections[1], ub);
  } // divider(T[], int, int)

  /**
   * Selects a piviot in the given sub array by averaging three random values.
   *
   * @param values an array.
   * @param lb     the lower bound of the sub-array.
   * @param ub     the upper bound of the sub-array.
   *
   * @return The index of the pivot found.
   */
  public int getPivot(T[] values, int lb, int ub) {
    int length = ub - lb;
    if (length > 2) {
      Random random = new Random();
      int first = random.nextInt(length);
      int second = random.nextInt(length);
      int third = random.nextInt(length);
      int firstSecond = this.order.compare(values[first], values[second]);
      int firstThird = this.order.compare(values[first], values[third]);
      int secondThird = this.order.compare(values[second], values[third]);

      if ((firstSecond >= 0 && firstThird <= 0) || (firstSecond <= 0 && firstThird >= 0)) {
        return first;
      } // if
      if ((firstSecond <= 0 && secondThird <= 0) || (firstSecond >= 0 && firstThird <= 0)) {
        return second;
      } // if
      if ((secondThird <= 0 && firstThird >= 0) || (secondThird >= 0 && firstThird <= 0)) {
        return third;
      } // if
    } // if
    return lb + ((ub - lb) / 2);
  } // getPivot(T[], int, int)

  /**
   * Partitions the sub array around the pivot it will find with getPivot.
   *
   * @param values an array.
   * @param lb     the lower bound of the sub-array.
   * @param ub     the upper bound of the sub-array.
   *
   * @return an array of two ints which corispind to the lower and upper bounds
   *         of the partitions in the array.
   */
  public int[] quicksort(T[] values, int lb, int ub) {
    T pivot = values[getPivot(values, lb, ub)];
    int current = lb;
    while (current < ub) {
      if (this.order.compare(values[current], pivot) < 0) {
        T temp = values[current];
        values[current] = values[lb];
        values[lb] = temp;
        lb++;
        current++;
      } else if (this.order.compare(values[current], pivot) > 0) {
        --ub;
        T temp = values[current];
        values[current] = values[ub];
        values[ub] = temp;
      } else {
        current++;
      } // else
    } // while
    return new int[] {lb, ub};
  } // quicksort(T[], int, int)
} // class quicksorter
