package sudoku.board;

@SuppressWarnings("rawtypes")
public interface Board<E extends Cell> {
    int getMaxIndex();

    void setCell(int index, E e);

    E getCell(int index);

    boolean isCellUpdatable(int index);

    void print();
}
