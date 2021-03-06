package adventofcode.y2021.day04;

import static adventofcode.y2021.Inputs.inputForDay;
import static java.lang.System.out;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.annotation.Nullable;

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

        public List<WinningBoard> findWinners(final List<Integer> drawnNumbers) {
            return boards.stream()
                .map(board -> solve(board, drawnNumbers))
                .filter(Objects::nonNull)
                .collect(toList());
        }

        @Nullable
        private WinningBoard solve(final Board board, final List<Integer> drawnNumbers) {
            out.println("solving:");
            
            int left = 0;
            int right = drawnNumbers.size() - 1;
            
            do {
                int i = (left + right) / 2;
                out.printf("left=%2d, right=%2d, i=%2d", left, right, i);
                List<Integer> numbers = drawnNumbers.subList(0, i + 1);
                if (board.isWinner(numbers)) {
                    out.println(" *");
                    right = i;
                } else {
                    out.println(" -");
                    left = i;
                }
            } while (right - left > 1);
            
            if (right == drawnNumbers.size() - 1 && !board.isWinner(drawnNumbers)) {
                return null;
            }
            
            out.printf("not solvable in %d, solvable in %d%n", left, right);
            return new WinningBoard(board, drawnNumbers.subList(0, right + 1));
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

    @AllArgsConstructor
    @Getter
    public static class WinningBoard {
        private final Board board;

        private final List<Integer> numbers;

        public int numberSize() {
            return numbers.size();
        }
    }

    Integer part1() {
        Bingo bingo = createBingo();

        List<Integer> numbersToDraw = parseAsInts(inputLines.get(0), ",");
        List<WinningBoard> winningBoards = bingo.findWinners(numbersToDraw);
        WinningBoard bestWinner = winningBoards.stream()
            .min(Comparator.comparingInt(WinningBoard::numberSize))
            .orElseThrow(IllegalStateException::new);
        return winnerBoardResult(bestWinner.getNumbers(), bestWinner.getBoard());
    }

    Integer part2() {
        Bingo bingo = createBingo();

        List<Integer> numbersToDraw = parseAsInts(inputLines.get(0), ",");
        List<WinningBoard> winningBoards = bingo.findWinners(numbersToDraw);
        WinningBoard leastWinner = winningBoards.stream()
            .max(Comparator.comparingInt(WinningBoard::numberSize))
            .orElseThrow(IllegalStateException::new);
        return winnerBoardResult(leastWinner.getNumbers(), leastWinner.getBoard());
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
}
