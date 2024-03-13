package sudoku.shogi;

import java.util.List;

import sudoku.board.Board;
import sudoku.shogi.koma.King;
import sudoku.shogi.koma.Koma;
import sudoku.shogi.player.ShogiPlayer;
import sudoku.shogi.point.Point;

public class ShogiBoard implements Board<ShogiCell> {
    private int size; // 将棋盤の一片の長さ
    private ShogiCell[][] board;
    private ShogiPlayer firstPlayer;
    private ShogiPlayer lastPlayer;

    public ShogiBoard(int size, ShogiCell[][] board, ShogiPlayer firstPlayer, ShogiPlayer lastPlayer) {
        this.size = size;
        this.board = board;
        this.firstPlayer = firstPlayer;
        this.lastPlayer = lastPlayer;
    }

    public int getMaxIndex() {
        return size * size;
    }

    public void setCell(int index, ShogiCell cell) {
        setCell(getPoint(index), cell);
    }

    public void setCell(Point point, ShogiCell cell) {
        board[point.getRow()][point.getColumn()] = cell;
    }

    public ShogiCell getCell(int index) {
        return getCell(getPoint(index));
    }

    public ShogiCell getCell(Point point) {
        return board[point.getRow()][point.getColumn()];
    }

    public boolean isCellUpdatable(int index) {
        Point nowPoint = getPoint(index);
        return isCellUpdatable(nowPoint);
    }

    public boolean isCellUpdatable(Point nowPoint) {
        ShogiCell cell = getCell(nowPoint);
        Koma koma = cell.getValue();
        List<Point> movableList = koma.getMovablePointList();
        for (Point movablePoint : movableList) {
            Point movedPoint = movablePoint.add(nowPoint);
            try {
                ShogiCell movedCell = getCell(movedPoint);
                // 王将の場合は敵の攻撃範囲内にあるかまで判定する
            } catch (IndexOutOfBoundsException e) {
                continue;
            }
            return true;
        }
        return false;
    }

    public int getIndex(Point point) {
        return point.getRow() * size + point.getColumn();
    }

    public Point getPoint(int index) {
        return new Point(index / size, index % size);
    }

    public ShogiPlayer getFirstPlayer() {
        return firstPlayer;
    }

    public ShogiPlayer getLastPlayer() {
        return lastPlayer;
    }

    /**
     * 
     * @param player
     * @return 発見できない場合は-1を返す
     */
    public int getKingCellIndex(ShogiPlayer player) {
        for (int i = 0; i < size * size; i++) {
            ShogiCell cell = getCell(i);
            Koma koma = cell.getValue();
            if (koma instanceof King && koma.getPlayer().equals(player))
                return i;
        }
        return -1;
    }

    public void print() {
        System.out.print("後手の持ち駒：");
        printRemainKomaOfPlayer(lastPlayer);
        printBoard();
        System.out.print("先手の持ち駒：");
        printRemainKomaOfPlayer(firstPlayer);
    }

    // 盤面の出力
    private void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int index = getIndex(new Point(i, j));
                ShogiCell cell = getCell(index);
                Koma koma = cell.getValue();
                if (koma == null) {
                    System.out.print("口");
                } else {
                    printKomaOfFirstPlayer(koma);
                }
            }
            System.out.println();
        }
    }

    private void printRemainKomaOfPlayer(ShogiPlayer player) {
        List<Koma> remainList = player.getRemainKoma();
        for (Koma koma : remainList) {
            printKomaOfFirstPlayer(koma);
            System.out.print(" ");
        }
        System.out.println();
    }

    private void printKomaOfFirstPlayer(Koma koma) {
        ShogiPlayer player = koma.getPlayer();
        if (player.equals(firstPlayer)) {
            System.out.print("\u001b[00;36m" + koma.toString() + "\u001b[00m");
        } else {
            System.out.print("\u001b[00;33m" + koma.toString() + "\u001b[00m");
        }
    }
}
