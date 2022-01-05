package adventofcode.y2021.day02;

public class UpV1CommandHandler extends SubmarineCommandHandlerBase<UpCommand> {
    public UpV1CommandHandler() {
        super(UpCommand.class);
    }

    @Override
    protected void doHandle(final UpCommand command, final Submarine submarine) {
        submarine.setDepth(submarine.getDepth() - command.getAmount());
    }
}
