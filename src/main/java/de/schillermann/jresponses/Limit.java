package de.schillermann.jresponses;

import java.io.IOException;

/**
 * The boundary of a cycle.
 */
public interface Limit {
  /**
   * Has the limit been reached?
   * @return True if we can continue, false if we must stop
   * @throws IOException If fails
   */
  boolean value() throws IOException;
}
