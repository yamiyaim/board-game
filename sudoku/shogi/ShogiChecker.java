package sudoku.shogi;

import sudoku.board.BoardChecker;
import sudoku.shogi.player.ShogiPlayer;

public class ShogiChecker implements BoardChecker<ShogiBoard> {
    /**
     * どちらかが王手の場合にTrue
     */
    public boolean check(ShogiBoard board) {
        ShogiPlayer firstPlayer = board.getFirstPlayer();
        ShogiPlayer lastPlayer = board.getLastPlayer();
        return isCheckMate(board, firstPlayer) || isCheckMate(board, lastPlayer);
    }

    public boolean isCheckMate(ShogiBoard board, ShogiPlayer player) {
        try {
            int kingIndex = board.getKingCellIndex(player);
            return !board.isCellUpdatable(kingIndex);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }
}
