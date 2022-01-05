package adventofcode.y2021.day02;

import java.util.Optional;

public class UpCommandParser implements SubmarineCommandParser {
    @Override
    public Optional<SubmarineCommand> parse(final String input) {
        if (input.startsWith("up")) {
            return Optional.of(
                new UpCommand(Integer.parseInt(input.split(" ")[1])));
        }
        return Optional.empty();
    }
}
