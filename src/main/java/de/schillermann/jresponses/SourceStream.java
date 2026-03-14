package de.schillermann.jresponses;

import java.io.InputStream;

/**
 * A source stream scalar.
 */
public final class SourceStream implements Scalar<InputStream> {
  private final InputStream stream;

  public SourceStream(final InputStream src) {
    this.stream = src;
  }

  @Override
  public InputStream value() {
    return this.stream;
  }
}
