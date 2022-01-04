package adventofcode.y2021.day02;

import static com.google.common.collect.Sets.newHashSet;

import java.util.Optional;
import java.util.Set;

import lombok.ToString;

@ToString
public class ForwardV1CommandParser implements SubmarineCommandParser {
    private final Set<String> names;

    public ForwardV1CommandParser() {
        this(newHashSet("forward"));
    }

    public ForwardV1CommandParser(final Set<String> names) {
        this.names = newHashSet(names);
    }

    public void addAlias(final String name) {
        names.add(name);
    }

    public void removeAlias(final String name) {
        names.remove(name);
    }

    @Override
    public Optional<SubmarineCommand> parse(final String input) {
        var tokens = input.split(" ");
        if (names.contains(tokens[0])) {
            try {
                return Optional.of(
                    new ForwardV1Command(Integer.parseInt(tokens[1])));
            } catch (final NumberFormatException e) {
                throw new IllegalArgumentException("illegal syntax or format for input: " + input, e);
            }
        }
        return Optional.empty();
    }
}
