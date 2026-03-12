package de.schillermann.jresponses;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;

public final class RequestBodyJson implements Scalar<JsonObject> {

  private final Request request;

  public RequestBodyJson(final Request request) {
    this.request = request;
  }

  @Override
  public JsonObject value() throws IOException {
    try (InputStream body = this.request.body();
        JsonReader reader = Json.createReader(body)) {
      return reader.readObject();
    }
  }
}
