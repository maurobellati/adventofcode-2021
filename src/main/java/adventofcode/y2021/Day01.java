package adventofcode.y2021;

import static adventofcode.y2021.Inputs.inputForDay;
import static java.lang.System.out;
import static java.util.stream.Collectors.toList;

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

        private Integer previous;

        public void accept(final int value) {
            if (previous == null) {
                previous = value;
                return;
            }
            if (previous < value) {
                increases++;
            }
            previous = value;
        }
    }

    public static Collector<Integer, MeasurementStats, MeasurementStats> collectingMeaseurements() {
        return Collector.of(
            MeasurementStats::new,
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
                MeasurementStats::new,
                MeasurementStats::accept,
                (x, y) -> {
                    throw new IllegalStateException("not supported");
                });
        return stats.getIncreases();
    }

    public long part1withCollector() {
        MeasurementStats stats = inputLines.stream().map(Integer::parseInt).collect(collectingMeaseurements());
        return stats.getIncreases();
    }

    long part1forLoop() {
        // Iterate over the indices
        // State var is defined outside the loop
        // Explicit increment of the state var
        List<Integer> values = inputLines.stream().map(Integer::parseInt).collect(toList());
        int increases = 0;
        for (int i = 1; i < values.size(); i++) {
            if (values.get(i - 1) < values.get(i)) {
                increases++;
            }
        }
        return increases;
    }

    Integer part2() {
        return 0;
    }
}
