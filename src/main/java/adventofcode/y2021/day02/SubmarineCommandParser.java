package adventofcode.y2021.day02;

import java.util.Optional;

public interface SubmarineCommandParser {
    Optional<SubmarineCommand> parse(String input);
}
