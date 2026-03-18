package de.schillermann.jresponses;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Accept and dispatch connections from a server socket.
 */
public final class Connections implements Scalar<Object> {
  private final ServerSocket server;
  private final Session session;
  private final Scalar<Integer> threads;
  private ExecutorService exec;

  public Connections(final ServerSocket srv, final Session sn) {
    this(
      srv,
      sn,
      () -> Runtime.getRuntime().availableProcessors()
    );
  }

  public Connections(
    final ServerSocket srv,
    final Session sn,
    final Scalar<Integer> thds
  ) {
    this.server = srv;
    this.session = sn;
    this.threads = thds;
  }

  @Override
  public Object value() throws IOException {
    if (Thread.currentThread().isInterrupted()) {
      if (this.exec != null) {
        this.exec.shutdown();
      }
      return new End();
    }
    if (this.exec == null) {
      this.exec = Executors.newFixedThreadPool(this.threads.value());
    }
    final Socket socket = this.server.accept();
    this.exec.submit(
      () -> {
        try (socket) {
          this.session.dispatch(socket);
        } catch (final IOException ex) {
          throw new IllegalStateException(ex);
        }
      }
    );
    return this.value();
  }
}
