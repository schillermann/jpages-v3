package de.schillermann.jresponses;

import java.io.IOException;

public final class FormattedText implements Text {

  private final String pattern;

  private final Object[] args;

  public FormattedText(final String pattern, final Object... arguments) {
    this.pattern = pattern;
    this.args = arguments;
  }

  @Override
  public String string() throws IOException {
    final Object[] processed = new Object[this.args.length];
    for (int idx = 0; idx < this.args.length; ++idx) {
      final Object arg = this.args[idx];
      if (arg instanceof Text) {
        processed[idx] = ((Text) arg).string();
      } else {
        processed[idx] = arg;
      }
    }
    return String.format(this.pattern, processed);
  }
}
