package sudoku.shogi;

import sudoku.board.BoardBuilder;
import sudoku.exception.InputException;
import sudoku.shogi.koma.Bishop;
import sudoku.shogi.koma.GoldGeneral;
import sudoku.shogi.koma.King;
import sudoku.shogi.koma.Knight;
import sudoku.shogi.koma.Koma;
import sudoku.shogi.koma.Lance;
import sudoku.shogi.koma.Pawn;
import sudoku.shogi.koma.Rook;
import sudoku.shogi.koma.SilverGeneral;
import sudoku.shogi.player.ShogiPlayer;
import sudoku.shogi.point.Point;

public class ShogiBuilder implements BoardBuilder<ShogiBoard> {

    public ShogiBoard build(int size, String board) {
        ShogiCell[][] shogiBoard = initBoard(size);

        ShogiPlayer firstPlayer = new ShogiPlayer();
        ShogiPlayer lastPlayer = new ShogiPlayer();

        String[] splittedBoard = board.split("\n");

        for (String komaString : splittedBoard) {
            try {
                String[] splittedChar = komaString.split(" ");
                Point point = getPoint(splittedChar);
                Koma koma = getKoma(splittedChar, firstPlayer, lastPlayer);
                if (point.getRow() == -1 && point.getColumn() == -1) {
                    ShogiPlayer player = koma.getPlayer();
                    player.getKoma(koma);
                } else {
                    shogiBoard[point.getRow()][point.getColumn()].setValue(koma);
                }
            } catch (IndexOutOfBoundsException e) {
                throw new InputException("範囲外の座標が指定されました");
            }
        }

        return new ShogiBoard(size, shogiBoard, firstPlayer, lastPlayer);
    }

    private ShogiCell[][] initBoard(int size) {
        ShogiCell[][] board = new ShogiCell[size + 1][size + 1];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new ShogiCell();
            }
        }
        return board;
    }

    private Point getPoint(String[] splittedChar) {
        int row = Integer.parseInt(splittedChar[0]);
        int column = Integer.parseInt(splittedChar[1]);
        return new Point(row - 1, column - 1); // 各行と列を0から始まる形に格納する
    }

    private ShogiPlayer getPlayer(String firstLast, ShogiPlayer firstPlayer, ShogiPlayer lastPlayer) {
        if (firstLast.equals("先"))
            return firstPlayer;
        if (firstLast.equals("後"))
            return lastPlayer;
        throw new InputException("先後のどちらかの値にならない文字列が入力されました");
    }

    private Koma getKoma(String[] splittedChar, ShogiPlayer firstPlayer, ShogiPlayer lastPlayer) {
        String firstLast = splittedChar[2];
        String komaName = splittedChar[3];
        ShogiPlayer player = getPlayer(firstLast, firstPlayer, lastPlayer);
        switch (komaName) {
            case "歩":
                return new Pawn(player);
            case "と":
                Koma pawn = new Pawn(player);
                pawn.promote();
                return pawn;
            case "香":
                return new Lance(player);
            case "成香":
                Koma lance = new Lance(player);
                lance.promote();
                return lance;
            case "桂":
                return new Knight(player);
            case "成桂":
                Koma knight = new Knight(player);
                knight.promote();
                return knight;
            case "銀":
                return new SilverGeneral(player);
            case "成銀":
                Koma silverGeneral = new SilverGeneral(player);
                silverGeneral.promote();
                return silverGeneral;
            case "金":
                return new GoldGeneral(player);
            case "玉":
                return new King(player);
            case "角":
                return new Bishop(player);
            case "馬":
                Koma bishop = new Bishop(player);
                bishop.promote();
                return bishop;
            case "飛":
                return new Rook(player);
            case "竜":
                Koma rook = new Rook(player);
                rook.promote();
                return rook;
            default:
                throw new InputException("駒名に合致しない文字列が入っています");
        }
    }
}
