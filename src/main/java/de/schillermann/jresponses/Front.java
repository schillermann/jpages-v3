package de.schillermann.jresponses;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public final class Front {
  private final Session session;
  private final int port;

  public Front(Session session, int port) {
    this.session = session;
    this.port = port;
  }

  public void listen() throws IOException {
    try (final ServerSocket server = new ServerSocket(this.port)) {
      new Cycle(
        new Limit() {
          @Override
          public boolean value() throws IOException {
            return !Thread.currentThread().isInterrupted();
          }
        },
        new Step() {
          @Override
          public Object value() throws IOException {
            try (final Socket socket = server.accept()) {
              Front.this.session.dispatch(socket);
            }
            return this;
          }
        }
      ).value();
    }
  }
}
