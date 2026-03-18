package de.schillermann.jresponses;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * An object representing a server socket.
 */
public final class ServerSocketOf implements Scalar<ServerSocket> {
  private final Scalar<Integer> port;

  public ServerSocketOf(final int prt) {
    this(() -> prt);
  }

  public ServerSocketOf(final Scalar<Integer> prt) {
    this.port = prt;
  }

  @Override
  public ServerSocket value() throws IOException {
    return new ServerSocket(this.port.value());
  }
}
