package de.schillermann.jresponses;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test case for {@link AverageLoad}.
 */
final class AverageLoadTest {

  @Test
  void calculatesRpsWithValidUptime() throws IOException {
    final long uptime = 2000; // 2 seconds
    final long total = 100;    // 100 requests
    final Conclusion report = new Report(0, uptime, total);
    final MemoryMedia media = new MemoryMedia();
    
    new AverageLoad(report).media(media);
    
    // 100 / (2000 / 1000.0) = 100 / 2.0 = 50.00
    assertEquals("50.00", media.value("rps"), "RPS should be 50.00 for 100 requests in 2 seconds");
    assertEquals(String.valueOf(uptime), media.value("uptime"), "Uptime should be preserved");
    assertEquals(String.valueOf(total), media.value("total"), "Total should be preserved");
  }

  @Test
  void handlesZeroUptime() throws IOException {
    final long uptime = 0;
    final long total = 100;
    final Conclusion report = new Report(0, uptime, total);
    final MemoryMedia media = new MemoryMedia();
    
    new AverageLoad(report).media(media);
    
    assertEquals("0.00", media.value("rps"), "RPS should be 0.00 when uptime is zero");
  }

  @Test
  void handlesSmallUptime() throws IOException {
    final long uptime = 500; // 0.5 seconds
    final long total = 10;   // 10 requests
    final Conclusion report = new Report(0, uptime, total);
    final MemoryMedia media = new MemoryMedia();
    
    new AverageLoad(report).media(media);
    
    // 10 / (500 / 1000.0) = 10 / 0.5 = 20.00
    assertEquals("20.00", media.value("rps"), "RPS should be 20.00 for 10 requests in 0.5 seconds");
  }
}
