package de.schillermann.jresponses;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Media that outputs to a java.util.logging.Logger.
 */
public final class LoggedMedia implements Media {
  private final Logger logger;
  private final Level level;

  public LoggedMedia() {
    this(Logger.getLogger(LoggedMedia.class.getName()));
  }

  public LoggedMedia(final Logger lgr) {
    this(lgr, Level.INFO);
  }

  public LoggedMedia(final Logger lgr, final Level lvl) {
    this.logger = lgr;
    this.level = lvl;
  }

  @Override
  public Media status(final int code, final String message) {
    this.logger.log(this.level, "Status: {0} {1}", new Object[]{code, message});
    return this;
  }

  @Override
  public Media header(final String name, final String value) {
    this.logger.log(this.level, "{0}: {1}", new Object[]{name, value});
    return this;
  }

  @Override
  public Media body(final InputStream stream) throws IOException {
    this.logger.log(this.level, "Body: (stream)");
    return this;
  }
}
