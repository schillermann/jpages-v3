package de.schillermann.jresponses;

import java.io.IOException;

public final class FormattedArg implements Scalar<Object> {
  private final Object origin;

  public FormattedArg(final Object obj) {
    this.origin = obj;
  }

  @Override
  public Object value() throws IOException {
    final Object res;
    if (this.origin instanceof Text) {
      res = ((Text) this.origin).string();
    } else {
      res = this.origin;
    }
    return res;
  }
}
