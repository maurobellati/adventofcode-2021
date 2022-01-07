package adventofcode.y2021.day02;

public class DownV2CommandHandler extends SubmarineCommandHandlerBase<DownCommand> {
    public DownV2CommandHandler() {
        super(DownCommand.class);
    }

    @Override
    protected void doHandle(final DownCommand command, final Submarine submarine) {
        submarine.setAim(submarine.getAim() + command.getAmount());
    }

    @Override
    protected void doUndo(final DownCommand command, final Submarine submarine) {
        submarine.setAim(submarine.getAim() - command.getAmount());
    }
}
