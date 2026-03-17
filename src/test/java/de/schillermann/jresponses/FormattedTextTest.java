package de.schillermann.jresponses;

import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class FormattedTextTest {

  @Test
  public void formatsTextWithMixedArguments() throws IOException {
    final String pattern = "Hello %s, today is %d. Response: %s";
    final Text textArg = new TextOf("200 OK");
    final String res = new FormattedText(
        pattern,
        "mario",
        17,
        textArg).string();
    Assertions.assertEquals(
        "Hello mario, today is 17. Response: 200 OK",
        res);
  }
}
