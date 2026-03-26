package de.schillermann.jresponses;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WireMediaTest {
    @Test
    void callsValueOnlyOnce() throws IOException {
        final AtomicInteger count = new AtomicInteger(0);
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final Scalar<OutputStream> scalar = () -> {
            count.incrementAndGet();
            return baos;
        };
        // Constructing WireMedia should NOT call value() yet (lazy)
        final Media media = new WireMedia(scalar);
        assertEquals(0, count.get(), "Constructor should be lazy");

        // First call to status should trigger value()
        final Media next = media.status(200, "OK");
        assertEquals(1, count.get(), "First operation should trigger value()");

        // Next operations should NOT call origin scalar's value() again
        next.header("X-Test", "Value").body(new InputStreamOf("Body"));
        assertEquals(1, count.get(), "Scalar.value() should be called only once throughout the chain");
    }
}
