package de.schillermann.jresponses;

import java.io.IOException;

/**
 * A header found at a cursor.
 */
public final class HeaderFromCursor implements Header {
  private final Cursor cursor;
  private final String name;

  public HeaderFromCursor(final Cursor crsr, final String key) {
    this.cursor = crsr;
    this.name = key;
  }

  @Override
  public String string() throws IOException {
    this.cursor.rewind();
    return new HeaderFound(this.cursor, this.name.toLowerCase() + ":").value();
  }

  @Override
  public boolean exists() throws IOException {
    return !this.string().isEmpty();
  }
}
