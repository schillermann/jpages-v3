package de.schillermann.jresponses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Port from the command line.
 *
 * Example: java -jar app.jar --port=8080
 */
public final class Port implements Scalar<Integer> {

    private final Iterable<String> args;

    public Port() {
        this(new ArrayList<>(0));
    }

    public Port(final String... args) {
        this(Arrays.asList(args));
    }

    public Port(final Iterable<String> args) {
        this.args = args;
    }

    @Override
    public Integer value() throws IOException {
        for (final String arg : this.args) {
            if (arg.startsWith("--port=")) {
                return Integer.parseInt(
                    arg.substring("--port=".length())
                );
            }
        }
        return Integer.getInteger("jresponses.port", 8080);
    }
}
