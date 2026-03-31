package de.schillermann.jresponses;

import java.io.IOException;

/**
 * A terminal object representing the conclusion of a process.
 */
public final class Conclusion implements Scalar<Conclusion> {
  @Override
  public Conclusion value() throws IOException {
    return this;
  }
}
