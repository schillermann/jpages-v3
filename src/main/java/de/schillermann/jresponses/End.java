package de.schillermann.jresponses;

import java.io.IOException;

/**
 * A terminal object representing the end of a process.
 */
public final class End implements Scalar<Object> {
  @Override
  public Object value() throws IOException {
    return this;
  }
}
