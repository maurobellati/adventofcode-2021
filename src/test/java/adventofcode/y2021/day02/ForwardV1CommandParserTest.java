package adventofcode.y2021.day02;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ForwardV1CommandParserTest {
    @Test
    void aliasesCanBeModifiedAtRuntime() {
        var forwardV1CommandParser = new ForwardV1CommandParser();

        assertThat(forwardV1CommandParser.parse("ff 1")).isEmpty();
        forwardV1CommandParser.addAlias("ff");

        assertThat(forwardV1CommandParser.parse("ff 1")).contains(new ForwardV1Command(1));

        forwardV1CommandParser.removeAlias("ff");
        assertThat(forwardV1CommandParser.parse("ff 1")).isEmpty();
    }
}
