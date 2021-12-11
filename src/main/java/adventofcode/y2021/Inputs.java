package adventofcode.y2021;

import static com.google.common.base.Charsets.UTF_8;
import static com.google.common.io.Resources.getResource;
import static com.google.common.io.Resources.readLines;
import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.google.common.base.Splitter;

public final class Inputs {
    public static final Splitter CSV_SPLITTER = Splitter.on(",").omitEmptyStrings().trimResults();

    public static List<String> inputForDay(final Integer day) {
        try {
            return readLines(getResource(Inputs.class, String.format("day-%s-input.txt", day)), UTF_8);
        } catch (final IOException e) {
            throw new IllegalArgumentException("Unable to read the input", e);
        }
    }

    public static List<String> lines(final String input) {
        return Splitter.on(lineSeparator()).trimResults().omitEmptyStrings().splitToList(input);
    }

    public static List<String> parseCsv(final String input) {
        return CSV_SPLITTER.splitToList(input);
    }

    public static <T> List<T> splitAndMap(final String aString, final String splitOn, final Function<String, T> mapper) {
        return Arrays.stream(aString.split(splitOn))
            .map(String::strip)
            .filter(it -> !it.isBlank())
            .map(mapper)
            .collect(toList());
    }
}
