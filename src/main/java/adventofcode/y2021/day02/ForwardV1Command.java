package adventofcode.y2021.day02;

import static com.google.common.base.Preconditions.checkArgument;

import lombok.Value;

@Value
public class ForwardV1Command implements SubmarineCommand {
    int distance;

    public ForwardV1Command(final int distance) {
        checkArgument(distance >= 0, "distance (%s) out of range: it must be non negative", distance);
        this.distance = distance;
    }

    @Override
    public void executeOn(final Submarine submarine) {
        submarine.setPosition(submarine.getPosition() + distance);
    }
}
