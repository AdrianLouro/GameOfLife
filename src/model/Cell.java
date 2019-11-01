package model;

public class Cell implements Cloneable {

    public final static Color ALIVE_COLOR = Color.RED;
    public final static Color DEAD_COLOR = Color.CYAN;

    private final Coordinate coordinate;
    private boolean isAlive;

    public Cell(Coordinate coordinate, boolean isAlive) {
        this.coordinate = coordinate;
        this.isAlive = isAlive;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(color())
                .append("[x]")
                .append(Color.RESET)
                .toString();
    }

    @Override
    protected Object clone() {
        return new Cell((Coordinate) coordinate.clone(), isAlive);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Cell cell = (Cell) object;
        return isAlive == cell.isAlive
                && (coordinate != null ? coordinate.equals(cell.coordinate) : cell.coordinate == null);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void isAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public int x() {
        return coordinate.x();
    }

    public int y() {
        return coordinate.y();
    }

    public Color color() {
        return isAlive() ? ALIVE_COLOR : DEAD_COLOR;
    }
}
