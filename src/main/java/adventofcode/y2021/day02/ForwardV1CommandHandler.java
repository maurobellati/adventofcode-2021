package adventofcode.y2021.day02;

public class ForwardV1CommandHandler extends SubmarineCommandHandlerBase<ForwardCommand> {
    public ForwardV1CommandHandler() {
        super(ForwardCommand.class);
    }

    @Override
    protected void doHandle(final ForwardCommand command, final Submarine submarine) {
        submarine.setPosition(submarine.getPosition() + command.getDistance());
    }
}
