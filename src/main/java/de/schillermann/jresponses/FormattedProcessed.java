package de.schillermann.jresponses;

import java.io.IOException;

/**
 * Processed arguments for FormattedText.
 */
public final class FormattedProcessed implements Scalar<Object[]> {

  /**
   * Arguments to process.
   */
  private final Object[] origin;

  /**
   * Current index.
   */
  private final int index;

  /**
   * Result array being filled.
   */
  private final Object[] result;

  /**
   * Ctor for the start of the chain.
   * @param args Arguments to process
   */
  public FormattedProcessed(final Object[] args) {
    this(args, 0, new Object[args.length]);
  }

  /**
   * Ctor for a specific step.
   * @param args Arguments to process
   * @param idx Index to process
   * @param res Result array
   */
  public FormattedProcessed(final Object[] args, final int idx, final Object[] res) {
    this.origin = args;
    this.index = idx;
    this.result = res;
  }

  @Override
  public Object[] value() throws IOException {
    final Object[] res;
    if (this.index < this.origin.length) {
      this.result[this.index] = new FormattedArg(this.origin[this.index]).value();
      res = new FormattedProcessed(this.origin, this.index + 1, this.result).value();
    } else {
      res = this.result;
    }
    return res;
  }
}
