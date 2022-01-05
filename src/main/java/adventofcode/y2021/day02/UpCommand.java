package adventofcode.y2021.day02;

import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class UpCommand implements SubmarineCommand {
    int amount;
}
