package de.schillermann.jresponses;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * A front-end exposed to the wire.
 */
public final class WireFront implements Front {
  private final Session session;
  private final Scalar<ServerSocket> socket;

  public WireFront(final Session sn, final Scalar<ServerSocket> skt) {
    this.session = sn;
    this.socket = skt;
  }

  @Override
  public Object value() throws IOException {
    try (final ServerSocket server = this.socket.value()) {
      return new Connections(server, this.session).value();
    }
  }
}
