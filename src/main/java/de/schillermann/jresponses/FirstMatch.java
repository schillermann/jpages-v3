package de.schillermann.jresponses;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Optional;

/**
 * First matching response in a series of forks.
 */
public final class FirstMatch implements Response {
  private final Request request;
  private final Iterator<Fork> forks;
  private final Response fallback;

  public FirstMatch(final Request req, final Iterator<Fork> frks, final Response fbk) {
    this.request = req;
    this.forks = frks;
    this.fallback = fbk;
  }

  @Override
  public void printTo(final OutputStream out) throws IOException {
    if (!this.forks.hasNext()) {
      this.fallback.printTo(out);
    } else {
      final Optional<Response> opt = this.forks.next().route(this.request);
      if (opt.isPresent()) {
        opt.get().printTo(out);
      } else {
        new FirstMatch(this.request, this.forks, this.fallback).printTo(out);
      }
    }
  }
}
