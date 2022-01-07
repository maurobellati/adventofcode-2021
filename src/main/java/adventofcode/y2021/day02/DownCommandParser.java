package adventofcode.y2021.day02;

import java.util.Optional;

public class DownCommandParser implements SubmarineCommandParser {
    @Override
    public Optional<SubmarineCommand> parse(final String input) {
        if (input.startsWith("down")) {
            return Optional.of(
                new DownCommand(Integer.parseInt(input.split(" ")[1])));
        }
        return Optional.empty();
    }
}
