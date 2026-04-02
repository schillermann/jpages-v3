package de.schillermann.jresponses;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Media that keeps headers in memory.
 */
public final class MemoryMedia implements Media {
  private final Map<String, String> headers;

  public MemoryMedia() {
    this(new HashMap<>());
  }

  public MemoryMedia(final Map<String, String> map) {
    this.headers = map;
  }

  @Override
  public Media status(final int code, final String message) {
    return this;
  }

  @Override
  public Media header(final String name, final String value) {
    this.headers.put(name, value);
    return this;
  }

  @Override
  public Media body(final InputStream stream) {
    return this;
  }

  public String value(final String name) {
    return this.headers.getOrDefault(name, "0");
  }
}
