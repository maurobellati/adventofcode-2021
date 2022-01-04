package adventofcode.y2021.day02;

import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class DownV1Command implements SubmarineCommand {
    int amount;

    @Override
    public void executeOn(final Submarine submarine) {
        submarine.setDepth(submarine.getDepth() + amount);
    }
}
