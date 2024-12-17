package edu.grinnell.csc207.sorting;

import org.junit.jupiter.api.BeforeAll;

/**
 * Tests of our Quickersorter.
 */
public class TestTrimbleAJSorter extends TestSorter {
  /**
   * Set up the sorters.
   */
  @BeforeAll
  static void setup() {
    stringSorter = new TrimbleAJSorter<String>((x,y) -> x.compareTo(y));
    intSorter = new TrimbleAJSorter<Integer>((x,y) -> x.compareTo(y));
  } // setup()

} // class TestQuicksorter
