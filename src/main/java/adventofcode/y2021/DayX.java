package adventofcode.y2021;

import static adventofcode.y2021.Inputs.inputForDay;
import static java.lang.System.out;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class DayX {
    public static void main(final String[] args) {
        out.println(new DayX(inputForDay(1)).part1());
        out.println(new DayX(inputForDay(1)).part2());
    }

    private final List<String> inputLines;

    Integer part1() {
        return 0;
    }

    Integer part2() {
        return 0;
    }
}
