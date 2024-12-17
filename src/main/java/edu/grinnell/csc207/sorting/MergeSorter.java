package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using merge sort.
 *
 * @param <T> The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 * @author A.J. Trimble
 */
public class MergeSorter<T> implements Sorter<T> {
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
  public MergeSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // MergeSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+
  /**
   * Sort an array in place using merge sort.
   *
   * @param values an array to sort.
   *
   * @post The array has been sorted according to some order (often one given
   *       to the constructor).
   * @post For all i, 0 &lt; i &lt; values.length, order.compare(values[i-1],
   *       values[i]) &lt;= 0
   */
  /*@Override
  public void sort(T[] values) {
    if (values.length <= 1) {
      return;
    } // if
    int midpoint = values.length / 2;
    T[] leftside = (T[]) new Object[midpoint];
    T[] rightside = (T[]) new Object[values.length - midpoint];
    for (int i = 0; i < midpoint; i++) {
      leftside[i] = values[i];
      rightside[i] = values[i + midpoint];
    } // for
    if (values.length % 2 != 0) {
      rightside[midpoint] = values[values.length - 1];
    } // if
    sort(leftside);
    sort(rightside);
    merge(leftside, rightside, values);
  } // sort(T[]) */


    /**
   * Sort an array in place using merge sort.
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
    if (values.length <= 1) {
      return;
    } // if
    T[] helper = (T[]) new Object[values.length];
    recursiveBody(0, values.length - 1, helper, values);
  }


  private void recursiveBody(int lb, int ub, T[] helper, T[] values) {
    // base case
    if (lb >= ub) {
      return;
    }
    // recusive case
    int midpoint = (lb + ub) / 2;
    recursiveBody(lb, midpoint, helper, values);
    recursiveBody(midpoint, ub + 1, helper, values);
    //merge(leftside, rightside, values);
  }

  /**
   * Merge two sub-arrays into a sorted array.
   *
   * @param leftside  one smaller sorted array.
   *
   * @param rightside one smaller sorted array.
   *
   * @param values    an array into which the sub arrays values will be placed.
   *
   
  public void merge(T[] leftside, T[] rightside, T[] values) {
    int leftsideIndex = 0;
    int rightsideIndex = 0;
    int mergedIndex = 0;
    while (leftsideIndex < leftside.length && rightsideIndex < rightside.length) {
      T leftElement = leftside[leftsideIndex];
      T rightElement = rightside[rightsideIndex];
      if (order.compare(leftElement, rightElement) <= 0) {
        values[mergedIndex] = leftElement;
        leftsideIndex++;
      } else {
        values[mergedIndex] = rightElement;
        rightsideIndex++;
      } // else
      mergedIndex++;
    } // while
    if (leftsideIndex < leftside.length) {
      System.arraycopy(leftside, leftsideIndex, values,
          mergedIndex, leftside.length - leftsideIndex);
    } else if (rightsideIndex < rightside.length) {
      System.arraycopy(rightside, rightsideIndex, values,
          mergedIndex, rightside.length - rightsideIndex);
    } // else
  } // merge(T[], T[], T[])

  */
} // class MergeSorter
