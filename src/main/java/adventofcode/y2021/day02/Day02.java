package adventofcode.y2021.day02;

import static adventofcode.y2021.Inputs.inputForDay;
import static java.lang.System.out;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class Day02 {
    public static void main(final String[] args) {
        out.println(new Day02(inputForDay(2)).part1());
        out.println(new Day02(inputForDay(2)).part2());
    }

    private final List<String> inputLines;

    Integer part1() {
        Submarine submarine = new Submarine();
        submarine.addCommandParser(new ForwardCommandParser());
        submarine.addCommandParser(new DownCommandParser());
        submarine.addCommandParser(new UpCommandParser());

        submarine.addCommandHandler(new ForwardV1CommandHandler());
        submarine.addCommandHandler(new UpV1CommandHandler());
        submarine.addCommandHandler(new DownV1CommandHandler());
        for (String line : inputLines) {
            submarine.handle(line);
        }
        return submarine.getPosition() * submarine.getDepth();
    }

    Integer part2() {
        Submarine submarine = new Submarine();
        submarine.addCommandParser(new ForwardCommandParser());
        submarine.addCommandParser(new DownCommandParser());
        submarine.addCommandParser(new UpCommandParser());

        submarine.addCommandHandler(new ForwardV2CommandHandler());
        submarine.addCommandHandler(new UpV2CommandHandler());
        submarine.addCommandHandler(new DownV2CommandHandler());
        for (String line : inputLines) {
            submarine.handle(line);
        }
        return submarine.getPosition() * submarine.getDepth();
    }
}

