package cleancode.minesweeper.tobe.minesweeper.board.cell;

public class CellState {

    private boolean isFlagged;
    private boolean isOpened;

    private CellState(boolean isFlagged, boolean isOpened) {
        this.isFlagged = isFlagged;
        this.isOpened = isOpened;
    }

    public static CellState initialize() {
        return new CellState(false, false);
    }

    //깃발 꽂기
    public void flag() {
        this.isFlagged = true;
    }

    //셀 열기
    public void open() {
        this.isOpened = true;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public boolean isFlagged() {
        return isFlagged;
    }
}
