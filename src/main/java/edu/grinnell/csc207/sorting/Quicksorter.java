package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using Quicksort.
 *
 * @param <T> The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 * @author A.J. Trimble
 */
public class Quicksorter<T> implements Sorter<T> {
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
  public Quicksorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // Quicksorter(Comparator)

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

  private void recursiveBody(T[] values, int lb, int ub) {
    if ((ub - lb) <= 1) {
      return;
    } // if
    int[] sections = partition(values, lb, ub);
    recursiveBody(values, lb, sections[0]);
    recursiveBody(values, sections[1], ub);
  } // recursiveBody(T[], int, int)

  private int[] partition(T[] values, int lb, int ub) {
    T pivot = values[lb + ((ub - lb) / 2)];
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
  } // partition(T[], int, int)
} // class Quicksorter
