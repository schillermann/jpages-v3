package de.schillermann.jresponses;

import java.io.IOException;
import java.io.InputStream;

/**
 * Find a specific header in an input stream.
 */
public final class HeaderLine implements Scalar<String> {
  private final InputStream source;
  private final String prefix;

  public HeaderLine(final InputStream stream, final String key) {
    this.source = stream;
    this.prefix = key.toLowerCase() + ":";
  }

  @Override
  public String value() throws IOException {
    return new HeaderFound(
      new CursorFromStream(new SourceStream(this.source)),
      this.prefix
    ).value();
  }
}
