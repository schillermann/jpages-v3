package de.schillermann.jresponses;

/**
 * Total requests of a conclusion.
 */
public final class RequestsOf implements Scalar<Long> {
  private final Conclusion conclusion;

  public RequestsOf(final Conclusion res) {
    this.conclusion = res;
  }

  @Override
  public Long value() {
    return this.conclusion.total();
  }
}
