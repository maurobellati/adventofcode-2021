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
        var forwardV1CommandParser = new ForwardV1CommandParser();
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
        submarine.addCommandParser(new ForwardV1CommandParser());
        submarine.addCommandParser(new DownV1CommandParser());
        submarine.setDepth(9999);

        assertThatIllegalArgumentException()
            .isThrownBy(() -> submarine.handle(command))
            .withMessageContaining(exceptionMessage);
    }

    @Test
    void commandsCanBeAddedAtRuntime() {
        assertThatIllegalArgumentException().isThrownBy(() -> submarine.handle("forward 1"));
        submarine.addCommandParser(new ForwardV1CommandParser());

        submarine.handle("forward 1");
        assertThat(submarine.getPosition()).isEqualTo(1);
    }

    @BeforeEach
    void setUp() {
        submarine = new Submarine();
    }
}




























// void supportsUndoAndRedo() {
// submarine.addCommandParser(new ForwardV1CommandParser());
// assertThat(submarine.canUndo()).isFalse();
// assertThat(submarine.canRedo()).isFalse();
//
// submarine.handle("forward 3");
// submarine.handle("forward 5");
//
// assertThat(submarine.getPosition()).isEqualTo(8);
// assertThat(submarine.canUndo());
// assertThat(submarine.canRedo()).isFalse();
//
// submarine.undo();
// assertThat(submarine.getPosition()).isEqualTo(3);
// assertThat(submarine.canUndo());
// assertThat(submarine.canRedo());
//
// submarine.undo();
// assertThat(submarine.getPosition()).isEqualTo(0);
// assertThat(submarine.canUndo()).isFalse();
// assertThat(submarine.canRedo());
//
// submarine.redo();
// submarine.redo();
//
// assertThat(submarine.getPosition()).isEqualTo(8);
// assertThat(submarine.canUndo());
// assertThat(submarine.canRedo()).isFalse();
// }
