package de.schillermann.jresponses;

import java.util.Arrays;

public final class MemoryInBytes implements Memory {
  private static final int MASK_UNSIGNED_BYTE = 0xFF;
  private byte[] buffer;
  private int count;

  public MemoryInBytes() {
    this(32);
  }

  public MemoryInBytes(final int capacity) {
    this.buffer = new byte[capacity];
    this.count = 0;
  }

  @Override
  public void remember(final int data) {
    if (this.count == this.buffer.length) {
      this.buffer = Arrays.copyOf(this.buffer, this.buffer.length * 2);
    }
    this.buffer[this.count] = (byte) data;
    this.count++;
  }

  /**
   * Recalls a byte from the buffer.
   *
   * @param position The position to recall from.
   * @return The byte value as an unsigned integer (0-255).
   *         The mask is used to prevent sign extension when converting
   *         from the signed byte type to the return type int.
   */
  @Override
  public int recall(final int position) {
    return this.buffer[position] & MASK_UNSIGNED_BYTE;
  }

  @Override
  public int size() {
    return this.count;
  }
}
