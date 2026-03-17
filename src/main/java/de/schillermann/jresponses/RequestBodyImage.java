package de.schillermann.jresponses;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public final class RequestBodyImage implements Scalar<BufferedImage> {

  private final Request request;

  public RequestBodyImage(final Request request) {
    this.request = request;
  }

  @Override
  public BufferedImage value() throws IOException {
    try (InputStream body = this.request.body()) {
      final BufferedImage image = ImageIO.read(body);
      if (image == null) {
        throw new IOException("Failed to decode image from request body");
      }
      return image;
    }
  }
}
