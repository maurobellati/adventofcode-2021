package adventofcode.y2021.day02;

public class UpV2CommandHandler extends SubmarineCommandHandlerBase<UpCommand> {
    public UpV2CommandHandler() {
        super(UpCommand.class);
    }

    @Override
    protected void doHandle(final UpCommand command, final Submarine submarine) {
        submarine.setAim(submarine.getAim() - command.getAmount());
    }
}
