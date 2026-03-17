package de.schillermann.jresponses;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Fake cursor for testing.
 */
public final class FakeCursor implements Cursor {

  private final byte[] data;

  private int currentPosition;

  public FakeCursor(final String text) {
    this(text.getBytes(Charset.defaultCharset()));
  }

  public FakeCursor(final byte[] data) {
    this.data = data;
    this.currentPosition = 0;
  }

  @Override
  public int current() throws IOException {
    if (!this.exists()) {
      throw new IOException("No more data");
    }
    return this.data[this.currentPosition] & 0xFF;
  }

  @Override
  public void next() throws IOException {
    this.currentPosition++;
  }

  @Override
  public boolean exists() throws IOException {
    return this.currentPosition < this.data.length;
  }

  @Override
  public void rewind() throws IOException {
    this.currentPosition = 0;
  }
}
