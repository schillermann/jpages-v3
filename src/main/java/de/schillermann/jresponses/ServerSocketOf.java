package de.schillermann.jresponses;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * An object representing a server socket.
 */
public final class ServerSocketOf implements Scalar<ServerSocket> {
  private final int port;

  public ServerSocketOf(final int prt) {
    this.port = prt;
  }

  @Override
  public ServerSocket value() throws IOException {
    return new ServerSocket(this.port);
  }
}
