package de.schillermann.jresponses;

import java.io.IOException;

/**
 * The end of a line (\r\n).
 */
public final class LineEnd implements Scalar<Conclusion> {
  private final Cursor cursor;

  public LineEnd(final Cursor crsr) {
    this.cursor = crsr;
  }

  @Override
  public Conclusion value() throws IOException {
    if (this.cursor.exists() && this.cursor.current() == '\r') {
      this.cursor.next();
      if (this.cursor.exists() && this.cursor.current() == '\n') {
        this.cursor.next();
      }
    }
    return new Report();
  }
}
