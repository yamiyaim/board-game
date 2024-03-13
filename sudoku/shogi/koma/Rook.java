package sudoku.shogi.koma;

import java.util.ArrayList;
import java.util.List;

import sudoku.shogi.player.ShogiPlayer;
import sudoku.shogi.point.Point;

public class Rook extends Koma {

    public Rook(ShogiPlayer player) {
        super(player);
        name = "飛";
        promotedName = "竜";
    }

    public List<Point> getMovablePointList() {
        List<Point> list = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            list.add(new Point(i, 0));
            list.add(new Point(-i, 0));
            list.add(new Point(0, i));
            list.add(new Point(0, -i));
        }
        if (isPromoted) {
            list.add(new Point(-1, -1));
            list.add(new Point(-1, 1));
            list.add(new Point(1, -1));
            list.add(new Point(1, 1));
        }
        return list;
    };

}
