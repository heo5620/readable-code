package cleancode.studycafe.tobe;

import cleancode.studycafe.tobe.exception.AppException;
import cleancode.studycafe.tobe.io.InputHandler;
import cleancode.studycafe.tobe.io.OutputHandler;
import cleancode.studycafe.tobe.io.StudyCafeFileHandler;
import cleancode.studycafe.tobe.model.StudyCafeLockerPass;
import cleancode.studycafe.tobe.model.StudyCafePass;
import cleancode.studycafe.tobe.model.StudyCafePassType;

import java.util.List;

public class StudyCafePassMachine {

    private final InputHandler inputHandler = new InputHandler();
    private final OutputHandler outputHandler = new OutputHandler();
    private final StudyCafeFileHandler studyCafeFileHandler = new StudyCafeFileHandler();

    public void run() {
        try {
            //스터디카페 안내 메시지
            outputHandler.showInfo();

            //이용권 타입 선택
            StudyCafePassType selectedPassType = getPassTypeFromUser();

            //이용권 선택
            StudyCafePass selectedPass = getPassFromUser(selectedPassType);

            //이용권 내역 출력
            showPassDetails(selectedPassType, selectedPass);
        } catch (AppException e) {
            outputHandler.showSimpleMessage(e.getMessage());
        } catch (Exception e) {
            outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
        }
    }

    /**
     * 사용자로부터 이용권 타입 입력 받아서 반환
     */
    private StudyCafePassType getPassTypeFromUser() {
        outputHandler.askPassTypeSelection();
        return inputHandler.getPassTypeSelectingUserAction();
    }

    /**
     * 사용자로부터 이용권 입력 받아서 반환
     */
    private StudyCafePass getPassFromUser(StudyCafePassType selectedPassType) {
        List<StudyCafePass> filteredPasses = filterStudyCafePassesByType(readStudyCafePassesFromFile(), selectedPassType);
        outputHandler.showPassListForSelection(filteredPasses);
        return inputHandler.getSelectPass(filteredPasses);
    }

    /**
     * 이용권 타입에 맞는 이용권 리스트 반환
     */
    private List<StudyCafePass> filterStudyCafePassesByType(List<StudyCafePass> studyCafePasses, StudyCafePassType selectedPassType) {
        return studyCafePasses.stream()
                .filter(studyCafePass -> studyCafePass.getPassType() == selectedPassType)
                .toList();
    }

    /**
     * 이용권 목록 파일 읽어오기
     */
    private List<StudyCafePass> readStudyCafePassesFromFile() {
        return studyCafeFileHandler.readStudyCafePasses();
    }

    /**
     * 이용권 내역 보여주기
     */
    private void showPassDetails(StudyCafePassType selectedPassType, StudyCafePass selectedPass) {
        outputHandler.showPassOrderSummary(selectedPass, getLockerPassIfFixed(selectedPassType, selectedPass));
    }

    /**
     * 고정권이라면 사물함 이용권 반환하기
     */
    private StudyCafeLockerPass getLockerPassIfFixed(StudyCafePassType selectedPassType, StudyCafePass selectedPass) {
        if (selectedPassType == StudyCafePassType.FIXED) {
            StudyCafeLockerPass filteredLockerPass = filterLockerPassByTypeAndDuration(readStudyCafeLockerPassesFromFile(), selectedPass);

            if (filteredLockerPass == null) {
                return null;
            }

            if (askForLockerUsage(filteredLockerPass)) {
                return filteredLockerPass;
            }
        }
        return null;
    }

    /**
     * 이용권의 타입과 기간이 맞는 사물함 이용권 반환
     */
    private StudyCafeLockerPass filterLockerPassByTypeAndDuration(List<StudyCafeLockerPass> lockerPasses, StudyCafePass selectedPass) {
        return lockerPasses.stream()
                .filter(option ->
                        option.getPassType() == selectedPass.getPassType() //이용권 타입
                                && option.getDuration() == selectedPass.getDuration() //이용권 기간
                )
                .findFirst()
                .orElse(null);
    }

    /**
     * 사물함 이용권 목록 파일 읽어오기
     */
    private List<StudyCafeLockerPass> readStudyCafeLockerPassesFromFile() {
        return studyCafeFileHandler.readLockerPasses();
    }

    /**
     * 사물함 이용 여부 묻기
     */
    private boolean askForLockerUsage(StudyCafeLockerPass filteredLockerPass) {
        outputHandler.askLockerPass(filteredLockerPass);
        return inputHandler.getLockerSelection();
    }

}
