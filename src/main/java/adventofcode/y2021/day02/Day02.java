package adventofcode.y2021.day02;

import static adventofcode.y2021.Inputs.inputForDay;
import static java.lang.Integer.parseInt;
import static java.lang.System.out;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class Day02 {
    public static void main(final String[] args) {
        out.println(new Day02(inputForDay(2)).part1());
        out.println(new Day02(inputForDay(2)).part2());
    }

    private final List<String> inputLines;

    Integer part1() {
        int position = 0;
        int depth = 0;
        for (String line : inputLines) {
            var tokens = line.split(" ");
            var command = tokens[0];
            var arg = parseInt(tokens[1]);
            switch (command) {
                case "forward" -> position += arg;
                case "down" -> depth += arg;
                case "up" -> depth -= arg;
                default -> throw new IllegalStateException("unexpected value: " + command);
            }
        }
        return position * depth;
    }

    Integer part2() {
        int position = 0;
        int depth = 0;
        int aim = 0;
        for (String line : inputLines) {
            var tokens = line.split(" ");
            var command = tokens[0];
            var arg = parseInt(tokens[1]);
            switch (command) {
                case "forward" -> {
                    position += arg;
                    depth += aim * arg;
                }
                case "down" -> aim += arg;
                case "up" -> aim -= arg;
                default -> throw new IllegalStateException("unexpected value: " + command);
            }
        }
        return position * depth;
    }
}
