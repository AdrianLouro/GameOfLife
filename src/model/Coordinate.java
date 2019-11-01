package model;

public class Coordinate implements Cloneable {

    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Coordinate coordinate = ((Coordinate) object);
        return x == coordinate.x
                && y == coordinate.y;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("(")
                .append(x)
                .append(", ")
                .append(y)
                .append(')')
                .toString();
    }

    @Override
    protected Object clone() {
        return new Coordinate(x, y);
    }
}
