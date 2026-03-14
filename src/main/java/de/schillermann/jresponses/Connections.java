package de.schillermann.jresponses;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Accept and dispatch connections from a server socket.
 */
public final class Connections implements Scalar<Object> {
  private final ServerSocket server;
  private final Session session;

  public Connections(final ServerSocket srv, final Session sn) {
    this.server = srv;
    this.session = sn;
  }

  @Override
  public Object value() throws IOException {
    if (Thread.currentThread().isInterrupted()) {
      return new End();
    }
    try (final Socket socket = this.server.accept()) {
      this.session.dispatch(socket);
    }
    return this.value();
  }
}
