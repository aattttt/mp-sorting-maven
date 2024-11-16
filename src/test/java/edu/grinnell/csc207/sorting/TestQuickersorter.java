package edu.grinnell.csc207.sorting;

import org.junit.jupiter.api.BeforeAll;

/**
 * Tests of our Quickersorter.
 */
public class TestQuickersorter extends TestSorter {
  /**
   * Set up the sorters.
   */
  @BeforeAll
  static void setup() {
    stringSorter = new Quickersorter<String>((x,y) -> x.compareTo(y));
    intSorter = new Quickersorter<Integer>((x,y) -> x.compareTo(y));
  } // setup()

} // class TestQuicksorter
