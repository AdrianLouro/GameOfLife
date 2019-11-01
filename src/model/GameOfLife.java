package model;

import static java.lang.System.out;
import static java.lang.Thread.sleep;

public class GameOfLife {

    private final Board board;
    private final int sleepMillis;
    private int turn;

    public GameOfLife(Board board, int sleepMillis) {
        this.board = board;
        this.sleepMillis = sleepMillis;
        this.turn = 0;
    }

    public void play() {
        printTurn();
        waitUntilNextTurn();

        while (!hasEnded()) {
            turn++;
            playTurn();
            printTurn();
            waitUntilNextTurn();
        }
    }

    private void playTurn() {
        final Board boardSnapshot = (Board) board.clone();

        for (int row = 0; row < board.rows(); row++) {
            for (int column = 0; column < board.columns(); column++) {
                final Cell cellSnapshot = boardSnapshot.cellAt(row, column);
                final int aliveNeighbours = boardSnapshot.aliveNeighboursOf(cellSnapshot).length;
                board.cellAt(row, column).isAlive(cellShouldBeAlive(cellSnapshot, aliveNeighbours));
            }
        }
    }

    private boolean cellShouldBeAlive(Cell cell, int aliveNeighbours) {
        return cell.isAlive()
                ? (aliveNeighbours >= 2 && aliveNeighbours <= 3)
                : aliveNeighbours == 3;
    }

    private boolean hasEnded() {

        return !board.hasAliveCells();
    }

    private void printTurn() {
        out.println("Generation: " + turn);
        out.println("Population: " + board.aliveCells());
        out.println(board);
    }

    private void waitUntilNextTurn() {
        try {
            sleep(sleepMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
