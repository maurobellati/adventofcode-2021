package adventofcode.y2021.day03;

import static adventofcode.y2021.Inputs.inputForDay;
import static java.lang.Integer.parseInt;
import static java.lang.System.out;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class Day03 {
    public static void main(final String[] args) {
        out.println(new Day03(inputForDay(1)).part1());
        out.println(new Day03(inputForDay(1)).part2());
    }

    private final List<String> inputLines;

    Integer part1() {
        return gamma() * epsilon();
    }

    private int epsilon() {
        return extractValue(Comparator.naturalOrder());
    }

    private int gamma() {
        return extractValue(Comparator.<Integer>naturalOrder().reversed());
    }

    private int extractValue(final Comparator<Integer> comparator) {
        var wordSize = inputLines.get(0).length();
        String resultAsString = IntStream.range(0, wordSize)
            .mapToObj(i -> getFrequencies(inputLines, i, comparator).get(0).getKey())
            .map(String::valueOf)
            .collect(joining());
        return parseInt(resultAsString, 2);
    }

    private List<Map.Entry<Character, List<String>>> getFrequencies(
        final List<String> lines,
        final int i,
        final Comparator<Integer> comparator) {
        return lines.stream()
            .collect(groupingBy(line -> line.charAt(i)))
            .entrySet()
            .stream()
            .sorted(Comparator.comparing(kv -> kv.getValue().size(), comparator))
            .collect(toList());
    }

    Integer part2() {
        return oxygenRating() * co2Rating();
    }

    private int co2Rating() {
        return find(inputLines, 0, Comparator.naturalOrder(), '0');
    }

    private int oxygenRating() {
        return find(inputLines, 0, Comparator.<Integer>naturalOrder().reversed(), '1');
    }

    private int find(final List<String> lines, final int i, final Comparator<Integer> comparator, final char defaultIfTie) {
        out.printf("findRecursive i=%s: %s%n", i, lines);
        List<Map.Entry<Character, List<String>>> frequencies = getFrequencies(lines, i, comparator);

        boolean tie = frequencies.size() > 1 && frequencies.get(0).getValue().size() == frequencies.get(1).getValue().size();
        Map.Entry<Character, List<String>> entry = tie ? frequencies.stream()
            .filter(kv -> kv.getKey().equals(defaultIfTie))
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("unable to findRecursive entry with char " + defaultIfTie))
            : frequencies.get(0);

        if (entry.getValue().size() == 1) {
            out.printf("  found i=%s: %s%n", i, entry);
            return parseInt(entry.getValue().get(0), 2);
        }
        return find(entry.getValue(), i + 1, comparator, defaultIfTie);
    }
}
