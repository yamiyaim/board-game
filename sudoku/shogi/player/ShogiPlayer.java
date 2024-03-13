package sudoku.shogi.player;

import java.util.ArrayList;
import java.util.List;

import sudoku.exception.InputException;
import sudoku.shogi.koma.Koma;

public class ShogiPlayer {
    List<Koma> remainKoma;

    public ShogiPlayer() {
        remainKoma = new ArrayList<>();
    }

    public void getKoma(Koma koma) {
        remainKoma.add(koma);
    }

    public void lostKoma(Koma koma) {
        if (!remainKoma.remove(koma)) {
            throw new InputException("該当のコマは持っていません");
        }
    }

    public List<Koma> getRemainKoma() {
        return remainKoma;
    }
}
