package adventofcode.y2021.day02;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SubmarineTest {
    private Submarine submarine;

    @Test
    void aliasesCanBeModifiedAtRuntime() {
        submarine.addCommandHandler(new ForwardV1CommandHandler());
        var forwardV1CommandParser = new ForwardCommandParser();
        submarine.addCommandParser(forwardV1CommandParser);

        assertThatIllegalArgumentException().isThrownBy(() -> submarine.handle("ff 1"));
        forwardV1CommandParser.addAlias("ff");

        submarine.handle("ff 1");
        assertThat(submarine.getPosition()).isEqualTo(1);

        forwardV1CommandParser.removeAlias("ff");
        assertThatIllegalArgumentException().isThrownBy(() -> submarine.handle("ff 1"));
    }

    @CsvSource({
            "forward xy , syntax",
            "forward 5.1, format",
            "forward -5 , range",
            "down 2     , spec",
    })
    @ParameterizedTest
    void differentValidations(final String command, final String exceptionMessage) {
        submarine.addCommandParser(new ForwardCommandParser());
        submarine.addCommandHandler(new ForwardV1CommandHandler());
        submarine.addCommandParser(new DownCommandParser());
        submarine.addCommandHandler(new DownV1CommandHandler());
        submarine.setDepth(9999);

        assertThatIllegalArgumentException()
            .isThrownBy(() -> submarine.handle(command))
            .withMessageContaining(exceptionMessage);
    }

    @Test
    void commandsCanBeAddedAtRuntime() {
        submarine.addCommandParser(new ForwardCommandParser());
        assertThatIllegalArgumentException().isThrownBy(() -> submarine.handle("forward 1"));
        submarine.addCommandHandler(new ForwardV1CommandHandler());

        submarine.handle("forward 1");
        assertThat(submarine.getPosition()).isEqualTo(1);
    }

    @Test
    void undoAndRedo() {
        submarine.addCommandHandler(new ForwardV1CommandHandler());

        submarine.handle(new ForwardCommand(1));
        submarine.handle(new ForwardCommand(2));
        submarine.handle(new ForwardCommand(3));

        assertThat(submarine.getPosition()).isEqualTo(6);

        submarine.undo();
        assertThat(submarine.getPosition()).isEqualTo(3);
        assertThat(submarine.getHistory())
            .containsExactly(new ForwardCommand(1), new ForwardCommand(2));

        submarine.undo();
        assertThat(submarine.getPosition()).isEqualTo(1);
        assertThat(submarine.getHistory())
            .containsExactly(new ForwardCommand(1));

        submarine.redo();
        assertThat(submarine.getPosition()).isEqualTo(3);
        assertThat(submarine.getHistory())
            .containsExactly(new ForwardCommand(1), new ForwardCommand(2));

        submarine.handle(new ForwardCommand(4));
        assertThat(submarine.getPosition()).isEqualTo(7);
        assertThat(submarine.getHistory())
            .containsExactly(new ForwardCommand(1), new ForwardCommand(2), new ForwardCommand(4));
    }

    @BeforeEach
    void setUp() {
        submarine = new Submarine();
    }
}
