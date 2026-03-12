package de.schillermann.jresponses;

import jakarta.json.JsonObject;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test case for {@link RequestBodyJson}.
 */
final class RequestBodyJsonTest {

  @Test
  void readsJsonBody() throws IOException {
    final String data = "POST / HTTP/1.1\r\nContent-Length: 15\r\n\r\n{\"key\":\"value\"}";
    final Request request = new RequestFromStream(new FakeCursor(data));
    final JsonObject json = new RequestBodyJson(request).value();
    assertEquals("value", json.getString("key"), "The JSON key 'key' should have value 'value'");
  }

  @Test
  void readsTextFromJsonBody() throws IOException {
    final String data = "POST / HTTP/1.1\r\nContent-Length: 16\r\n\r\n{\"name\":\"mario\"}";
    final Request request = new RequestFromStream(new FakeCursor(data));
    final Text text = new TextOfJson(
        new StickyScalar<>(new RequestBodyJson(request)),
        "name");
    assertEquals("mario", text.string(), "The JSON key 'name' should have value 'mario'");
  }
}
