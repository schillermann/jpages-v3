package de.schillermann.jresponses;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Accept and dispatch connections from a server socket.
 */
public final class Connections implements Front {
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

  /**
   * Dispatched connections.
   * @return The conclusion of the process
   * @throws IOException If fails
   */
  public Conclusion conclusion() throws IOException {
    final long start = System.currentTimeMillis();
    final AtomicLong requests = new AtomicLong();
    try {
      this.exec = Executors.newFixedThreadPool(this.threads.value());
    } catch (final Exception ex) {
      if (Thread.currentThread().isInterrupted()) {
        return new Report(start, System.currentTimeMillis(), 0);
      }
      throw new IOException(ex);
    }
    try {
      while (!Thread.currentThread().isInterrupted()) {
        final Socket socket;
        try {
          socket = this.server.accept();
        } catch (final IOException ex) {
          if (Thread.currentThread().isInterrupted()) {
            return new Report(
              start,
              System.currentTimeMillis(),
              requests.get()
            );
          }
          throw ex;
        } catch (final Exception ex) {
          if (Thread.currentThread().isInterrupted()) {
            return new Report(
              start,
              System.currentTimeMillis(),
              requests.get()
            );
          }
          throw new RuntimeException(ex);
        }
        this.exec.submit(
          () -> {
            try (socket) {
              requests.incrementAndGet();
              this.session.dispatch(socket);
            } catch (final IOException ex) {
              throw new IllegalStateException(ex);
            }
          }
        );
      }
    } finally {
      if (this.exec != null) {
        this.exec.shutdown();
      }
    }
    return new Report(
      start,
      System.currentTimeMillis(),
      requests.get()
    );
  }
}

