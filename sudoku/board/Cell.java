package sudoku.board;

public interface Cell<T> {
    T getValue();

    void setValue(T t);

    boolean equals(Cell<T> c);
}
