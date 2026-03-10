package de.schillermann.jresponses;

import java.io.IOException;

public final class LineFromCursor implements Line {
  private final Cursor cursor;

  public LineFromCursor(final Cursor crsr) {
    this.cursor = crsr;
  }

  @Override
  public String string() throws IOException {
    final StringBuilder buf = new StringBuilder();
    new Cycle(
        new Limit() {
          @Override
          public boolean value() throws IOException {
            return LineFromCursor.this.cursor.exists()
                && LineFromCursor.this.cursor.current() != '\r';
          }
        },
        new Step() {
          @Override
          public Object value() throws IOException {
            buf.append((char) LineFromCursor.this.cursor.current());
            LineFromCursor.this.cursor.next();
            return this;
          }
        }).value();
    if (this.cursor.exists() && this.cursor.current() == '\r') {
      this.cursor.next();
      if (this.cursor.exists() && this.cursor.current() == '\n') {
        this.cursor.next();
      }
    }
    return buf.toString();
  }
}
