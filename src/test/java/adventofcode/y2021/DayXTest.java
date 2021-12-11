package adventofcode.y2021;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DayXTest {
    private DayX day;

    @Test
    void part01() {
        assertThat(day.part1()).isEqualTo(0);
    }

    @Test
    void part02() {
        assertThat(day.part2()).isEqualTo(0);
    }

    @BeforeEach
    void setUp() {
        day = new DayX(Arrays.asList(""));
    }
}
