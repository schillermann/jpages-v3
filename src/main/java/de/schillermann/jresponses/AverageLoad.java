package de.schillermann.jresponses;

import java.io.IOException;

/**
 * Average load (requests per second).
 */
public final class AverageLoad implements Conclusion {
  private final Conclusion origin;

  public AverageLoad(final Conclusion conclusion) {
    this.origin = conclusion;
  }

  @Override
  public Media media(final Media media) throws IOException {
    final MemoryMedia memory = new MemoryMedia();
    this.origin.media(memory);
    final long uptime = Long.parseLong(memory.value("uptime"));
    final long total = Long.parseLong(memory.value("total"));
    final double rps;
    if (uptime > 0) {
      rps = (double) total / (uptime / 1000.0);
    } else {
      rps = 0.0;
    }
    return this.origin.media(media)
        .header("rps", String.format("%.2f", rps));
  }
}
