package sudoku.board;

@SuppressWarnings("rawtypes")
public interface BoardChecker<T extends Board> {
    boolean check(T t);
}
