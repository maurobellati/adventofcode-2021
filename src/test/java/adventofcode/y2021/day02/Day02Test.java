package adventofcode.y2021.day02;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Day02Test {
    private Day02 day;

    @Test
    void part01() {
        assertThat(day.part1()).isEqualTo(150);
    }

    @Test
    void part02() {
        assertThat(day.part2()).isEqualTo(900);
    }

    @BeforeEach
    void setUp() {
        day = new Day02(
            List.of(
                "forward 5",
                "down 5",
                "forward 8",
                "up 3",
                "down 8",
                "forward 2"));
    }
}
