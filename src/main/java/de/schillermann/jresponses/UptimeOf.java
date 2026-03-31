package de.schillermann.jresponses;

/**
 * Uptime of a conclusion.
 */
public final class UptimeOf implements Scalar<Long> {
  private final Conclusion conclusion;

  public UptimeOf(final Conclusion res) {
    this.conclusion = res;
  }

  @Override
  public Long value() {
    return this.conclusion.uptime();
  }
}
