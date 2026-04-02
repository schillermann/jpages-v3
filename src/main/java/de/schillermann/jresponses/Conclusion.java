package de.schillermann.jresponses;

import java.io.IOException;

/**
 * A terminal object representing the conclusion of a process.
 */
public interface Conclusion {
  /**
   * Represent this conclusion in the media.
   * 
   * @param media The media
   * @return The updated media
   * @throws IOException If something goes wrong
   */
  Media media(Media media) throws IOException;
}
