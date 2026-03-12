package de.schillermann.jresponses;

import jakarta.json.JsonObject;
import java.io.IOException;

public final class TextOfJson implements Text {

  private final Scalar<JsonObject> json;

  private final String key;

  public TextOfJson(final Scalar<JsonObject> json, final String key) {
    this.json = json;
    this.key = key;
  }

  @Override
  public String string() throws IOException {
    return this.json.value().getString(this.key);
  }
}
