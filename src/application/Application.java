package application;

import model.Board;
import model.Coordinate;
import model.GameOfLife;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.*;

public class Application {

    @FunctionalInterface
    private interface BoardGenerator {
        Board get();
    }

    private static final Map<Integer, BoardGenerator> boards = new HashMap<Integer, BoardGenerator>() {{
        put(1, () -> new Board(
                5,
                5,
                new Coordinate[]{
                        new Coordinate(2, 1),
                        new Coordinate(2, 2),
                        new Coordinate(2, 3)
                }
        ));

        put(2, () -> new Board(
                6,
                5,
                new Coordinate[]{
                        new Coordinate(2, 1),
                        new Coordinate(2, 2),
                        new Coordinate(2, 3),
                        new Coordinate(2, 4)
                }
        ));

        put(3, () -> new Board(
                10,
                10,
                new Coordinate[]{
                        new Coordinate(2, 2),
                        new Coordinate(2, 3),
                        new Coordinate(2, 4),
                        new Coordinate(3, 2),
                        new Coordinate(3, 3),
                        new Coordinate(3, 4),
                        new Coordinate(4, 2),
                        new Coordinate(4, 3),
                        new Coordinate(4, 4),
                        new Coordinate(5, 5),
                        new Coordinate(5, 6),
                        new Coordinate(5, 7),
                        new Coordinate(6, 5),
                        new Coordinate(6, 6),
                        new Coordinate(6, 7),
                        new Coordinate(7, 5),
                        new Coordinate(7, 6),
                        new Coordinate(7, 7),
                }
        ));
    }};

    public static void main(String[] args) {
        out.println("------Conway's Game of Life------\n");
        final Board board = askUserForBoard();
        final int sleepMillis = askUserForTurnDuration();
        out.println("");
        new GameOfLife(board, sleepMillis).play();
        out.println("\n------Game Over------");
    }

    private static Board askUserForBoard() {
        out.print("Please, select a board " + boards.keySet() + ": ");
        return boards.get(new Scanner(in).nextInt()).get();
    }

    private static int askUserForTurnDuration() {
        out.print("Please, select turns duration in milliseconds: ");
        return new Scanner(in).nextInt();
    }
}
