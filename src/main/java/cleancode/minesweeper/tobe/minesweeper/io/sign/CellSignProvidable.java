package cleancode.minesweeper.tobe.minesweeper.io.sign;

import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshot;

public interface CellSignProvidable {

    boolean surpports(CellSnapshot cellSnapshot);

    String provide(CellSnapshot cellSnapshot);
}
