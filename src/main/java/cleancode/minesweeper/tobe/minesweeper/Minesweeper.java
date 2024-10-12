package cleancode.minesweeper.tobe.minesweeper;

import cleancode.minesweeper.tobe.minesweeper.board.GameBoard;
import cleancode.minesweeper.tobe.minesweeper.config.GameConfig;
import cleancode.minesweeper.tobe.minesweeper.exception.GameException;
import cleancode.minesweeper.tobe.game.GameInitializable;
import cleancode.minesweeper.tobe.game.GameRunnable;
import cleancode.minesweeper.tobe.minesweeper.io.InputHandler;
import cleancode.minesweeper.tobe.minesweeper.io.OutputHandler;
import cleancode.minesweeper.tobe.minesweeper.board.position.CellPosition;
import cleancode.minesweeper.tobe.minesweeper.user.UserAction;

public class Minesweeper implements GameInitializable, GameRunnable {

    private final GameBoard gameBoard;
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;

    public Minesweeper(GameConfig gameConfig) {
        gameBoard = new GameBoard(gameConfig.getGameLevel());
        this.inputHandler = gameConfig.getInputHandler();
        this.outputHandler = gameConfig.getOutputHandler();
    }

    @Override
    public void initialize() {
        gameBoard.initializeGame(); //보드 초기화
    }

    @Override
    public void run() {
        outputHandler.showGameStartComments(); //게임 시작 메시지 출력

        while (gameBoard.isInProgress()) {
            try {
                outputHandler.showBoard(gameBoard); //현재 보드 출력

                CellPosition cellPosition = getCellInputFromUser(); //좌표 선택
                UserAction userAction = getUserActionInputFromUser(); //깃발 꽂을 지, 셀을 열지 선택
                actOnCell(cellPosition, userAction);
            } catch (GameException e) {
                outputHandler.showExceptionMessage(e);
            } catch (Exception e) {
                outputHandler.SHOWSimpleMessage("프로그램에 문제가 생겼습니다.");
            }
        }

        outputHandler.showBoard(gameBoard); //현재 보드 출력

        if (gameBoard.isWinStatus()) {
            outputHandler.showGameWinningComment();
        }
        if (gameBoard.isLoseStatus()) {
            outputHandler.showGameLosingComment();
        }
    }

    private CellPosition getCellInputFromUser() {
        outputHandler.showCommentForSelectingCell();
        CellPosition cellPosition = inputHandler.getCellPositionFromUser();
        if (gameBoard.isInvalidCellPosition(cellPosition)) {
            throw new GameException("잘못된 좌표를 선택하셨습니다.");
        }
        return cellPosition;
    }

    private UserAction getUserActionInputFromUser() {
        outputHandler.showCommentForUserAction();
        return inputHandler.getUserActionFromUser();
    }

    private void actOnCell(CellPosition cellPosition, UserAction userAction) {
        //사용자가 깃발을 꽂았다면
        if (doesUserChooseToPlantFlag(userAction)) {
            gameBoard.flagAt(cellPosition);
            return; //리턴하면 이제 이 상황을 고려할 필요가 없음
        }

        //사용자가 셀을 열었다면
        if (doesUserChooseToOpenCell(userAction)) {
            gameBoard.openAt(cellPosition);
            return;
        }
        throw new GameException("잘못된 번호를 선택하셨습니다.");
    }

    private boolean doesUserChooseToPlantFlag(UserAction userAction) {
        return userAction == UserAction.FLAG;
    }

    private boolean doesUserChooseToOpenCell(UserAction userAction) {
        return userAction == UserAction.OPEN;
    }

}
