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
public class Quickersorter<T> implements Sorter<T> {
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
  public Quickersorter(Comparator<? super T> comparator) {
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
    }
    recursiveBody(values, 0, values.length);
  } // sort(T[])

  public void recursiveBody(T[] values, int lb, int ub) {
    if ((ub - lb) <= 1) {
      return;
    }
    int[] sections = Quicksort(values, lb, ub);
    recursiveBody(values, lb, sections[0]);
    recursiveBody(values, sections[1], ub);
  } // divider(T[], int, int)

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
      }
      if ((firstSecond <= 0 && secondThird <= 0) || (firstSecond >= 0 && firstThird <= 0)) {
        return second;
      }
      if ((secondThird <= 0 && firstThird >= 0) || (secondThird >= 0 && firstThird <= 0)) {
        return third;
      }
    }
    return lb + ((ub - lb) / 2);
  } // getPivot(T[], int, int)

  public int[] Quicksort(T[] values, int lb, int ub) {
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
      }
    }
    return new int[] { lb, ub };
  } // Quicksort(T[], int, int)
} // class Quicksorter
