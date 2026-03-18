package de.schillermann.jresponses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Number of connections from the command line.
 *
 * Example: java -jar app.jar --connections=16
 */
public final class NumberOfConnections implements Scalar<Integer> {

    private final Iterable<String> args;

    public NumberOfConnections() {
        this(new ArrayList<>(0));
    }

    public NumberOfConnections(final String... args) {
        this(Arrays.asList(args));
    }

    public NumberOfConnections(final Iterable<String> args) {
        this.args = args;
    }

    @Override
    public Integer value() throws IOException {
        for (final String arg : this.args) {
            if (arg.startsWith("--connections=")) {
                return Integer.parseInt(
                    arg.substring("--connections=".length())
                );
            }
        }
        return Integer.getInteger(
            "jresponses.connections", 
            Math.max(4, Runtime.getRuntime().availableProcessors())
        );
    }
}
