package cleancode.minesweeper.tobe.io.sign;

import cleancode.minesweeper.tobe.cell.CellSnapshot;

public interface CellSignProvidable {

    boolean surpports(CellSnapshot cellSnapshot);

    String provide(CellSnapshot cellSnapshot);
}
