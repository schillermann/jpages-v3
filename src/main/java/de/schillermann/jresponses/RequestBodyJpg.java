package de.schillermann.jresponses;

import java.awt.image.BufferedImage;
import java.io.IOException;

public final class RequestBodyJpg implements Scalar<BufferedImage> {

  private final Scalar<BufferedImage> origin;

  private final Request request;

  public RequestBodyJpg(final Request request) {
    this(new RequestBodyImage(request), request);
  }

  public RequestBodyJpg(final Scalar<BufferedImage> origin, final Request request) {
    this.origin = origin;
    this.request = request;
  }

  @Override
  public BufferedImage value() throws IOException {
    final String type = this.request.header("Content-Type").string();
    if (!"image/jpeg".equals(type) && !"image/jpg".equals(type)) {
      throw new IOException(
          String.format("Expected image/jpeg content type, but got '%s'", type));
    }
    return this.origin.value();
  }
}
