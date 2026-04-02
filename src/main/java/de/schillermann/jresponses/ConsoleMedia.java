package de.schillermann.jresponses;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * Media that outputs to a PrintStream (e.g. System.out).
 */
public final class ConsoleMedia implements Media {
  private final PrintStream out;

  public ConsoleMedia() {
    this(System.out);
  }

  public ConsoleMedia(final PrintStream prnt) {
    this.out = prnt;
  }

  @Override
  public Media status(final int code, final String message) {
    this.out.printf("HTTP/1.1 %d %s\n", code, message);
    return this;
  }

  @Override
  public Media header(final String name, final String value) {
    this.out.printf("%s: %s\n", name, value);
    return this;
  }

  @Override
  public Media body(final InputStream stream) throws IOException {
    stream.transferTo(this.out);
    return this;
  }
}
