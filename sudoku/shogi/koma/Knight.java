package sudoku.shogi.koma;

import java.util.ArrayList;
import java.util.List;

import sudoku.shogi.player.ShogiPlayer;
import sudoku.shogi.point.Point;

public class Knight extends Koma {

    public Knight(ShogiPlayer player) {
        super(player);
        name = "桂";
        promotedName = "成桂";
    }

    public List<Point> getMovablePointList() {
        List<Point> list = new ArrayList<>();
        list.add(new Point(-1, -1));
        list.add(new Point(-1, 1));
        if (isPromoted) {
            list.add(new Point(-1, 0));
            list.add(new Point(0, -1));
            list.add(new Point(0, 1));
            list.add(new Point(1, 0));
        }
        return list;
    };
}
