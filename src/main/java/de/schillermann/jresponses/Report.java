package de.schillermann.jresponses;

import java.io.IOException;

/**
 * A terminal object representing the conclusion of a process.
 */
public final class Report implements Conclusion {
  private final long start;
  private final long end;
  private final long requests;

  public Report() {
    this(0, 0, 0);
  }

  public Report(final long st, final long ed, final long reqs) {
    this.start = st;
    this.end = ed;
    this.requests = reqs;
  }

  @Override
  public Media media(final Media mda) throws IOException {
    return mda.header("uptime", String.valueOf(this.end - this.start))
        .header("total", String.valueOf(this.requests));
  }
}
