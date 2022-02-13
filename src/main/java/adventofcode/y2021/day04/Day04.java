package adventofcode.y2021.day04;

import static adventofcode.y2021.Inputs.inputForDay;
import static com.google.common.base.Preconditions.checkState;
import static java.lang.System.out;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
class Day04 {
    public static void main(final String[] args) {
        out.println(new Day04(inputForDay(4)).part1());
        out.println(new Day04(inputForDay(4)).part2());
    }

    private final List<String> inputLines;

    @ToString
    public static class Bingo {
        @Getter
        private final List<Board> boards;

        public Bingo(final List<Board> boards) {
            this.boards = new ArrayList<>(boards);
        }

        public List<Board> findWinners(final List<Integer> drawnNumbers) {
            return boards.stream().filter(board -> board.isWinner(drawnNumbers)).collect(toList());
        }

        public void removeBoards(final List<Board> input) {
            boards.removeAll(input);
        }
    }

    @ToString
    public static class Board {
        @Getter
        private final List<List<Integer>> rows = new ArrayList<>();

        public boolean isWinner(final List<Integer> drawnNumbers) {
            return Stream.concat(getRows().stream(), getColumns().stream())
                .anyMatch(drawnNumbers::containsAll);
        }

        public List<List<Integer>> getColumns() {
            return IntStream.range(0, getColumnCount())
                .mapToObj(i -> rows.stream().map(row -> row.get(i)).collect(toList()))
                .collect(toList());
        }

        private int getColumnCount() {
            return rows.get(0).size();
        }

        public List<Integer> unmarkedNumbers(final List<Integer> drawnNumbers) {
            List<Integer> result = allNumbers();
            result.removeAll(drawnNumbers);
            return result;
        }

        private List<Integer> allNumbers() {
            return rows.stream().flatMap(Collection::stream).collect(toList());
        }

        void addRow(final List<Integer> row) {
            rows.add(row);
        }
    }

    Integer part1() {
        Bingo bingo = createBingo();

        List<Integer> numbersToDraw = parseAsInts(inputLines.get(0), ",");
        for (int i = 0; i < numbersToDraw.size(); i++) {
            List<Integer> drawnNumbers = numbersToDraw.subList(0, i + 1);
            List<Board> winners = bingo.findWinners(drawnNumbers);
            if (winners.isEmpty()) {
                out.printf("%s) no winners%n", i);
            } else {
                Board winner = winners.get(0);
                return winnerBoardResult(drawnNumbers, winner);
            }
        }
        return 0;
    }

    private int winnerBoardResult(final List<Integer> drawnNumbers, final Board winner) {
        List<Integer> unmarked = winner.unmarkedNumbers(drawnNumbers);
        int sum = unmarked.stream().mapToInt(it -> it).sum();
        out.printf("Winner: %s%n, unmarked=%s, sum=%s%n", winner, unmarked, sum);
        return sum * drawnNumbers.get(drawnNumbers.size() - 1);
    }

    private Bingo createBingo() {
        List<Board> boards = new ArrayList<>();
        inputLines.stream().skip(1).forEach(line -> {
            if (line.isBlank()) {
                boards.add(new Board());
            } else {
                Board current = boards.get(boards.size() - 1);
                current.addRow(parseAsInts(line, "\s+"));
            }
        });
        return new Bingo(boards);
    }

    private List<Integer> parseAsInts(final String input, final String separator) {
        return Arrays.stream(input.trim().split(separator)).map(Integer::parseInt).collect(toList());
    }

    Integer part2() {
        Bingo bingo = createBingo();

        List<Integer> numbersToDraw = parseAsInts(inputLines.get(0), ",");
        for (int i = 0; i < numbersToDraw.size(); i++) {
            List<Integer> drawnNumbers = numbersToDraw.subList(0, i + 1);
            List<Board> winners = bingo.findWinners(drawnNumbers);
            if (winners.isEmpty()) {
                out.printf("%s) no winners%n", i);
                continue;
            }

            out.printf("%s) winners(%s): %s%n", i, winners.size(), winners);

            List<Board> boardsLeft = bingo.getBoards();
            if (boardsLeft.size() == 1) {
                Board leastWinner = boardsLeft.get(0);
                checkState(winners.size() == 1 && winners.get(0).equals(leastWinner), "something wrong: %s", boardsLeft);

                return winnerBoardResult(drawnNumbers, leastWinner);
            }

            bingo.removeBoards(winners);
            out.printf("  removing winners. Boards left: %s%n", bingo.getBoards().size());
        }
        return 0;
    }
}
