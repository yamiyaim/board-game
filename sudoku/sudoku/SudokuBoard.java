package sudoku.sudoku;

import sudoku.board.Board;

public class SudokuBoard implements Board<SudokuCell> {
    private SudokuCell[][] board;
    private int size;
    private int maxIndex;

    /**
     * コンストラクタ
     * 
     * @param size  ：盤面の一片の長さにルートをかけた値
     * @param board ：実際の盤面
     */
    public SudokuBoard(int size, SudokuCell[][] board) {
        this.size = size;
        this.board = board;
        this.maxIndex = (int) Math.pow(size, 4);
    }

    /**
     * 盤面に割り振られたインデックスの上限を返す
     */
    public int getMaxIndex() {
        return maxIndex;
    }

    public int getSize() {
        return size;
    }

    /**
     * 該当するマスが擬似値を持っているか判定する
     * 基本擬似値以外は変更不可と考える
     * 
     * @return 擬似値の場合はtrueを、以外はfalseを返す
     */
    public boolean isCellUpdatable(int index) {
        return getCell(index).isPseudoValue();
    }

    public SudokuCell getCell(int index) {
        return board[getRowNumOfCell(index)][getColumnNumOfCell(index)];
    }

    public SudokuCell getCellFromRowAndColumn(int row, int column) {
        return board[row][column];
    }

    public SudokuCell getCellFromSquareNums(int outter, int inner) {
        return getCell(getIndexOfSquare(outter, inner));
    }

    public void setCell(int index, SudokuCell cell) {
        board[getRowNumOfCell(index)][getColumnNumOfCell(index)] = cell;
    }

    /**
     * indexの解き方の一元化
     * 
     * @param index
     * @return 行番号
     */
    public int getRowNumOfCell(int index) {
        return index % (size * size);
    }

    /**
     * indexの解き方の一元化
     * 
     * @param index
     * @return 列番号
     */
    public int getColumnNumOfCell(int index) {
        return index / (size * size);
    }

    /**
     * 外の大きい正四角形での順番と中の正四角形での順番からインデックスを導く
     * 
     * @param outter
     * @param inner
     * @return
     */
    public int getIndexOfSquare(int outter, int inner) {
        int[] baseOutter = getRowAndColumn(outter, true);
        int[] baseInner = getRowAndColumn(inner, false);
        return baseOutter[0] + baseInner[0] + (baseOutter[1] + baseInner[1]) * size * size;
    }

    /**
     * 正四角形の番号付けに対する座標を返す、isBaseがtrueの場合は全体正四角形の中にある小正四角形の座標を返す
     * 
     * @param number ：正四角形分解し、左上から順に右に繋ぎ、行の終わりごとに次の列を付け足した時の番号
     * @param isBase ：小正四角形の場合はfalse、外の正四角形の場合はtrue
     * @return [行座標、列座標]
     */
    public int[] getRowAndColumn(int number, boolean isBase) {
        int[] result = new int[2];
        result[0] = number / size * ((isBase) ? size : 1);
        result[1] = number % size * ((isBase) ? size : 1);
        return result;
    }

    /**
     * 数独の盤面を標準出力する
     */
    public void print() {
        for (SudokuCell[] row : board) {
            StringBuilder sb = new StringBuilder();
            for (SudokuCell cell : row) {
                sb.append(cell.getValue()).append(" ");
            }
            sb.delete(sb.length() - 1, sb.length());
            System.out.println(sb.toString());
        }
    }
}
