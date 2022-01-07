package adventofcode.y2021.day02;

public class ForwardV2CommandHandler extends SubmarineCommandHandlerBase<ForwardCommand> {
    public ForwardV2CommandHandler() {
        super(ForwardCommand.class);
    }

    @Override
    protected void doHandle(final ForwardCommand command, final Submarine submarine) {
        submarine.setPosition(submarine.getPosition() + command.getDistance());
        submarine.setDepth(submarine.getDepth() + submarine.getAim() * command.getDistance());
    }

    @Override
    protected void doUndo(final ForwardCommand command, final Submarine submarine) {
        submarine.setPosition(submarine.getPosition() - command.getDistance());
        submarine.setDepth(submarine.getDepth() - submarine.getAim() * command.getDistance());
    }
}
