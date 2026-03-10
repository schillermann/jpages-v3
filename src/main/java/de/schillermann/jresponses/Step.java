package de.schillermann.jresponses;

import java.io.IOException;

/**
 * A single action in a cycle.
 */
public interface Step {
  /**
   * Perform the step.
   * @return This step
   * @throws IOException If fails
   */
  Object value() throws IOException;
}
