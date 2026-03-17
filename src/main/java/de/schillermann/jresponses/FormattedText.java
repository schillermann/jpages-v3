package de.schillermann.jresponses;

import java.io.IOException;

public final class FormattedText implements Text {

  private final String pattern;

  private final Object[] args;

  public FormattedText(final String ptn, final Object... arguments) {
    this.pattern = ptn;
    this.args = arguments;
  }

  @Override
  public String string() throws IOException {
    return String.format(
        this.pattern,
        new FormattedProcessed(this.args).value());
  }
}
