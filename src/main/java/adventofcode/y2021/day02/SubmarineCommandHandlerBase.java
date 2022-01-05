package adventofcode.y2021.day02;

import static com.google.common.base.Preconditions.checkArgument;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public abstract class SubmarineCommandHandlerBase<T extends SubmarineCommand> implements SubmarineCommandHandler {
    @Getter
    private final Class<T> commandClass;

    @Override
    public boolean canHandle(final SubmarineCommand command) {
        return commandClass.isAssignableFrom(command.getClass());
    }

    @Override
    public void handle(final SubmarineCommand command, final Submarine submarine) {
        checkArgument(canHandle(command), "unable to handle %s: expected type is %s", command, commandClass);
        doHandle((T)command, submarine);
    }

    protected abstract void doHandle(final T command, final Submarine submarine);
    
    @Override
    public void undo(final SubmarineCommand command, final Submarine submarine) {
        checkArgument(canHandle(command), "unable to handle %s: expected type is %s", command, commandClass);
        doUndo((T)command, submarine);
    }

    protected void doUndo(final T command, final Submarine submarine) {}
}
