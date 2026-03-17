package de.schillermann.jresponses;

import java.awt.image.BufferedImage;
import java.io.IOException;

public final class RequestBodyPng implements Scalar<BufferedImage> {

  private final Scalar<BufferedImage> origin;

  private final Request request;

  public RequestBodyPng(final Request request) {
    this(new RequestBodyImage(request), request);
  }

  public RequestBodyPng(final Scalar<BufferedImage> origin, final Request request) {
    this.origin = origin;
    this.request = request;
  }

  @Override
  public BufferedImage value() throws IOException {
    final String type = this.request.header("Content-Type").string();
    if (!"image/png".equals(type)) {
      throw new IOException(
          String.format("Expected image/png content type, but got '%s'", type));
    }
    return this.origin.value();
  }
}
