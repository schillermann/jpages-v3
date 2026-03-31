package de.schillermann.jresponses;

import java.io.IOException;

/**
 * A terminal object representing the conclusion of a process.
 */
public final class Conclusion implements Scalar<Conclusion> {
  private final long start;
  private final long end;
  private final long requests;

  public Conclusion() {
    this(0, 0, 0);
  }

  public Conclusion(final long st, final long ed, final long reqs) {
    this.start = st;
    this.end = ed;
    this.requests = reqs;
  }

  @Override
  public Conclusion value() throws IOException {
    return this;
  }

  /**
   * The uptime in milliseconds.
   * @return Uptime
   */
  public long uptime() {
    return this.end - this.start;
  }

  /**
   * Total requests handled.
   * @return Number of requests
   */
  public long total() {
    return this.requests;
  }
}
