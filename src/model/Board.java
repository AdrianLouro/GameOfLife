package model;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.deepEquals;
import static java.util.Arrays.stream;

public class Board implements Cloneable {

    private final Cell[][] cells;

    public Board(int rows, int columns, Coordinate[] alives) {
        this.cells = buildBoard(rows, columns, alives);
    }

    public Board(Cell[][] cells) {
        this.cells = cells;
    }

    public int rows() {
        return cells.length;
    }

    public int columns() {
        return cells[0].length;
    }

    public Cell cellAt(int row, int column) {
        return this.cellAt(new Coordinate(column, row));
    }

    public Cell cellAt(Coordinate coordinate) {
        return this.cells[coordinate.y()][coordinate.x()];
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();

        for (int row = 0; row < rows(); row++) {
            for (int column = 0; column < columns(); column++) {
                stringBuilder
                        .append(cellAt(row, column).color())
                        .append("[")
                        .append(aliveNeighboursOf(cellAt(row, column)).length)
                        .append("]")
                        .append(Color.RESET);
            }
            stringBuilder.append('\n');
        }

        return stringBuilder.toString();
    }

    @Override
    protected Object clone() {
        Cell[][] clonedCells = new Cell[rows()][columns()];

        for (int row = 0; row < rows(); row++) {
            for (int column = 0; column < columns(); column++) {
                clonedCells[row][column] = (Cell) cellAt(row, column).clone();
            }
        }

        return new Board(clonedCells);
    }

    @Override
    public boolean equals(Object object) {
        return this == object
                || !(object == null || getClass() != object.getClass())
                && deepEquals(cells, ((Board) object).cells);

    }

    public boolean hasAliveCells() {
        return aliveCells() > 0;
    }

    public int aliveCells() {
        int aliveCells = 0;

        for (int row = 0; row < rows(); row++) {
            for (int column = 0; column < columns(); column++) {
                if (cellAt(row, column).isAlive()) {
                    aliveCells++;
                }
            }
        }

        return aliveCells;
    }

    public Cell[] aliveNeighboursOf(Cell cell) {
        return stream(neighboursOf(cell)).filter(Cell::isAlive).toArray(Cell[]::new);
    }

    private Cell[] neighboursOf(Cell cell) {
        final List<Cell> neighbours = new ArrayList<>();
        final int[] deltas = new int[]{-1, 0, 1};

        for (int deltaX : deltas) {
            for (int deltaY : deltas) {
                final int neighbourX = cell.x() + deltaX;
                final int neighbourY = cell.y() + deltaY;

                if (hasCellAt(neighbourX, neighbourY) && !cellAt(neighbourY, neighbourX).equals(cell)) {
                    neighbours.add(cellAt(neighbourY, neighbourX));
                }
            }

        }

        return neighbours.toArray(new Cell[neighbours.size()]);
    }

    private Cell[][] buildBoard(int rows, int columns, Coordinate[] alives) {
        final Cell[][] cells = new Cell[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                cells[row][column] = new Cell(new Coordinate(column, row), shouldSetAlive(row, column, alives));
            }
        }

        return cells;
    }

    private boolean hasCellAt(int x, int y) {
        return x >= 0 && y >= 0 && x < columns() && y < rows();
    }

    private boolean shouldSetAlive(int row, int column, Coordinate[] alives) {
        return stream(alives).anyMatch(coordinate -> coordinate.equals(new Coordinate(column, row)));
    }
}
