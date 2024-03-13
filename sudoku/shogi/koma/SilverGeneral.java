package sudoku.shogi.koma;

import java.util.ArrayList;
import java.util.List;

import sudoku.shogi.player.ShogiPlayer;
import sudoku.shogi.point.Point;

public class SilverGeneral extends Koma {

    public SilverGeneral(ShogiPlayer player) {
        super(player);
        name = "銀";
        promotedName = "成銀";
    }

    public List<Point> getMovablePointList() {
        List<Point> list = new ArrayList<>();
        list.add(new Point(-1, -1));
        list.add(new Point(-1, 0));
        list.add(new Point(-1, 1));
        if (!isPromoted) {
            list.add(new Point(1, -1));
            list.add(new Point(1, 1));
        } else {
            list.add(new Point(0, -1));
            list.add(new Point(0, 1));
            list.add(new Point(1, 0));
        }
        return list;
    };
}
