package adventofcode.y2021.day02;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = {"commandHandlers", "commandParsers"})
public class Submarine {
    private static final int MAX_DEPTH = 10_000;

    private int aim;

    private final List<SubmarineCommandHandler> commandHandlers = new ArrayList<>();

    private final List<SubmarineCommandParser> commandParsers = new ArrayList<>();

    private int depth;

    private int position;

    public void addCommandHandler(final SubmarineCommandHandler handler) {
        commandHandlers.add(handler);
    }

    public void addCommandParser(final SubmarineCommandParser parser) {
        commandParsers.add(parser);
    }

    public void handle(final SubmarineCommand command) {
        var handler = commandHandlers.stream()
            .filter(it -> it.canHandle(command))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("unable to find an handler for " + command));
        handler.handle(command, this);
        System.out.println("handled: " + command + " ->" + this);
    }

    public void handle(final String command) {
        handle(parse(command));
    }

    public void setDepth(final int depth) {
        checkArgument(depth <= MAX_DEPTH, "depth (%s) is out of spec: max depth = %s", depth, MAX_DEPTH);
        this.depth = depth;
    }

    private SubmarineCommand parse(final String command) {
        return commandParsers.stream()
            .map(it -> it.parse(command))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("unexpected value: " + command));
    }
}
