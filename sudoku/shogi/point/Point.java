package sudoku.shogi.point;

public class Point {
    private int row;
    private int column;

    public Point(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Point add(Point point) {
        return new Point(row + point.getRow(), column + point.getColumn());
    }
}
