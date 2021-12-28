package adventofcode.y2021.day01;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day01Test {
    private Day01 day;

    @Test
    void part01() {
        assertThat(day.part1forLoop()).isEqualTo(7);
        assertThat(day.part1intStream()).isEqualTo(7);
        assertThat(day.part1withCollect()).isEqualTo(7);
        assertThat(day.part1withCollector()).isEqualTo(7);
    }

    @Test
    void part02() {
        assertThat(day.part2forLoop()).isEqualTo(5);
        assertThat(day.part2withCollector()).isEqualTo(5);
    }

    @BeforeEach
    void setUp() {
        day = new Day01(
            List.of(
                "199", // i = 0
                "200", // i = 1
                "208",
                "210", // i = 3
                "200",
                "207",
                "240",
                "269",
                "260",
                "263" // i = 9
            ));
    }
}
