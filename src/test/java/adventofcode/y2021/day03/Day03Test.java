package adventofcode.y2021.day03;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day03Test {
    private Day03 day;

    @Test
    void part01() {
        assertThat(day.part1()).isEqualTo(198);
    }

    @Test
    void part02() {
        assertThat(day.part2()).isEqualTo(230);
    }

    @BeforeEach
    void setUp() {
        day = new Day03(
            List.of(
                "00100",
                "11110",
                "10110",
                "10111",
                "10101",
                "01111",
                "00111",
                "11100",
                "10000",
                "11001",
                "00010",
                "01010"));
    }
}
