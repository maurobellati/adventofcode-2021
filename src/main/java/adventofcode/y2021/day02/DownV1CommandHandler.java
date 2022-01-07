package adventofcode.y2021.day02;

public class DownV1CommandHandler extends SubmarineCommandHandlerBase<DownCommand> {
    public DownV1CommandHandler() {
        super(DownCommand.class);
    }

    @Override
    protected void doHandle(final DownCommand command, final Submarine submarine) {
        submarine.setDepth(submarine.getDepth() + command.getAmount());
    }

    @Override
    protected void doUndo(final DownCommand command, final Submarine submarine) {
        submarine.setDepth(submarine.getDepth() - command.getAmount());
    }
}
