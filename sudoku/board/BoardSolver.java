package sudoku.board;

@SuppressWarnings("rawtypes")
public interface BoardSolver<T extends Board> {
    T solve(T t);
}
