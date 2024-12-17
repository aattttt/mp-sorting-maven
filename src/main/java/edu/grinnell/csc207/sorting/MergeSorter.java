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
  @Override
  public void sort(T[] values) { 
    if (values.length <= 1) {
      return;
    } // if
    @SuppressWarnings("unchecked")
    T[] helper = (T[]) new Object[values.length];
    System.arraycopy(values, 0, helper, 0,values.length);
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
    recursiveBody(midpoint + 1, ub, helper, values);
    merge(lb, midpoint, ub, helper, values);
  }

  private void merge(int lb, int midpoint, int ub, T[] helper, T[]values) {
    int mergedIndex = lb;
    int leftsideIndex = lb;
    int rightsideIndex = midpoint + 1;
    
    while (leftsideIndex <= midpoint && rightsideIndex <= ub) {
      T leftElement = helper[leftsideIndex];
      T rightElement = helper[rightsideIndex];
      if (order.compare(leftElement, rightElement) <= 0) {
        values[mergedIndex] = leftElement;
        leftsideIndex++;
      } else {
        values[mergedIndex] = rightElement;
        rightsideIndex++;
      } // else
      mergedIndex++;
    } // while
    if (leftsideIndex <= midpoint) {
      System.arraycopy(helper, leftsideIndex, values,
          mergedIndex, midpoint - leftsideIndex + 1);
    } else if (rightsideIndex <= ub) {
      System.arraycopy(helper, rightsideIndex, values,
          mergedIndex, ub - rightsideIndex + 1);
    } // else
  } 
} // class MergeSorter

