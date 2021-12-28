package adventofcode.y2021.day01;

import static adventofcode.y2021.Inputs.inputForDay;
import static java.lang.System.out;
import static java.util.stream.Collectors.toList;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
class Day01 {
    public static class MeasurementStats {
        @Getter
        private int increases;

        private final int step;

        private final Deque<Integer> window;

        public MeasurementStats(final int step) {
            this.step = step;
            window = new ArrayDeque<>();
        }

        public void accept(final int value) {
            if (window.size() < step) {
                window.addFirst(value);
                return;
            }
            if (window.pollLast() < value) {
                increases++;
            }
            window.addFirst(value);
        }
    }

    public static Collector<Integer, MeasurementStats, MeasurementStats> collectingMeaseurements(final int step) {
        return Collector.of(
            () -> new MeasurementStats(step),
            MeasurementStats::accept,
            (x, y) -> {
                throw new IllegalStateException("not supported");
            });
    }

    public static void main(final String[] args) {
        out.println(new Day01(inputForDay(1)).part1forLoop());
        out.println(new Day01(inputForDay(1)).part2());
    }

    private final List<String> inputLines;

    public long part1intStream() {
        // Iterate over the indices
        // no state variable
        List<Integer> values = inputLines.stream().map(Integer::parseInt).collect(toList());
        return IntStream.range(1, values.size())
            .filter(i -> values.get(i - 1) < values.get(i))
            .count();
    }

    public long part1withCollect() {
        MeasurementStats stats = inputLines.stream()
            .map(Integer::parseInt)
            .collect(
                () -> new MeasurementStats(1),
                MeasurementStats::accept,
                (x, y) -> {
                    throw new IllegalStateException("not supported");
                });
        return stats.getIncreases();
    }

    public long part1withCollector() {
        MeasurementStats stats = inputLines.stream().map(Integer::parseInt).collect(collectingMeaseurements(1));
        return stats.getIncreases();
    }

    public long part2forLoop() {
        return forLoop(3);
    }

    public long part2withCollector() {
        MeasurementStats stats = inputLines.stream().map(Integer::parseInt).collect(collectingMeaseurements(3));
        return stats.getIncreases();
    }

    long part1forLoop() {
        // Iterate over the indices
        // State var is defined outside the loop
        // Explicit increment of the state var
        return forLoop(1);
    }

    Integer part2() {
        return 0;
    }

    private long forLoop(final int step) {
        List<Integer> values = inputLines.stream().map(Integer::parseInt).collect(toList());
        int increases = 0;
        for (int i = step; i < values.size(); i++) {
            if (values.get(i - step) < values.get(i)) {
                increases++;
            }
        }
        return increases;
    }
}
