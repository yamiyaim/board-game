package sudoku.board;

@SuppressWarnings("rawtypes")
public interface BoardBuilder<T extends Board> {
    T build(int size, String board);
}
