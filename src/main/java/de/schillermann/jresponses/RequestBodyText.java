package de.schillermann.jresponses;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public final class RequestBodyText implements Scalar<String> {

  private final Request request;

  public RequestBodyText(final Request request) {
    this.request = request;
  }

  @Override
  public String value() throws IOException {
    try (InputStream body = this.request.body()) {
      return new String(body.readAllBytes(), Charset.defaultCharset());
    }
  }
}
