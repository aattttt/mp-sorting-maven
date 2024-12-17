package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using selection sort.
 *
 * @param <T> The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 * @author A.J. Trimble
 */
public class SelectionSorter<T> implements Sorter<T> {
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
  public SelectionSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // SelectionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+
  /**
   * Sort an array in place using selection sort.
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
    if (values.length != 0) {
      T temp;
      for (int i = 0; i < values.length; i++) {
        int smallest = selectSmallest(values, i);
        if (smallest != i) {
          temp = values[i];
          values[i] = values[smallest];
          values[smallest] = temp;
        } // if
      } // for
    } // if
  } // sort(T[])

  /**
   * Find the smallest element in an array starting at index i.
   *
   * @param values the array to find the index in.
   * @param start  the index in the array the method will begin at.
   *
   * @return the index of the smallest element.
   */
  private int selectSmallest(T[] values, int start) {
    int smallest = start;
    for (int i = start + 1; i < values.length; i++) {
      if (this.order.compare(values[i], values[smallest]) < 0) {
        smallest = i;
      } // if
    } // for
    return smallest;
  } // selectSmallest(T[], int)
} // class SelectionSorter
