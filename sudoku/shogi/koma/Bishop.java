package sudoku.shogi.koma;

import java.util.ArrayList;
import java.util.List;

import sudoku.shogi.player.ShogiPlayer;
import sudoku.shogi.point.Point;

public class Bishop extends Koma {

    public Bishop(ShogiPlayer player) {
        super(player);
        name = "角";
        promotedName = "馬";
    }

    public List<Point> getMovablePointList() {
        List<Point> list = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            list.add(new Point(i, i));
            list.add(new Point(i, -i));
            list.add(new Point(-i, i));
            list.add(new Point(-i, -i));
        }
        if (isPromoted) {
            list.add(new Point(-1, 0));
            list.add(new Point(0, -1));
            list.add(new Point(0, 1));
            list.add(new Point(1, 0));
        }
        return list;
    };
}
