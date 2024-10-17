package cleancode.studycafe.tobe.model.order;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StudyCafePassOrderTest {

    @DisplayName("시간권 이용 시 합계를 계산한다.")
    @Test
    void calculateTotalPriceForHourlyPass() {
        //given
        StudyCafeSeatPass twoHourSeatPass = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 2, 4000, 0);
        StudyCafeSeatPass fourHourSeatPass = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 4, 6500, 0);
        StudyCafeSeatPass sixHourSeatPass = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 6, 9000, 0);
        StudyCafeSeatPass eightHourSeatPass = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 8, 11000, 0);
        StudyCafeSeatPass tenHourSeatPass = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 10, 12000, 0);
        StudyCafeSeatPass twelveHourSeatPass = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 12, 13000, 0);
        StudyCafeLockerPass lockerPass = null;

        StudyCafePassOrder twoHourOrder = new StudyCafePassOrder(twoHourSeatPass, lockerPass);
        StudyCafePassOrder fourHourOrder = new StudyCafePassOrder(fourHourSeatPass, lockerPass);
        StudyCafePassOrder sixHourOrder = new StudyCafePassOrder(sixHourSeatPass, lockerPass);
        StudyCafePassOrder eightHourOrder = new StudyCafePassOrder(eightHourSeatPass, lockerPass);
        StudyCafePassOrder tenHourOrder = new StudyCafePassOrder(tenHourSeatPass, lockerPass);
        StudyCafePassOrder twelveHourOrder = new StudyCafePassOrder(twelveHourSeatPass, lockerPass);

        //when
        int twoHourTotalPrice = twoHourOrder.getTotalPrice();
        int fourHourTotalPrice = fourHourOrder.getTotalPrice();
        int sixHourTotalPrice = sixHourOrder.getTotalPrice();
        int eightHourTotalPrice = eightHourOrder.getTotalPrice();
        int tenHourTotalPrice = tenHourOrder.getTotalPrice();
        int twelveHourTotalPrice = twelveHourOrder.getTotalPrice();

        //then
        assertThat(twoHourTotalPrice).isEqualTo(4000);
        assertThat(fourHourTotalPrice).isEqualTo(6500);
        assertThat(sixHourTotalPrice).isEqualTo(9000);
        assertThat(eightHourTotalPrice).isEqualTo(11000);
        assertThat(tenHourTotalPrice).isEqualTo(12000);
        assertThat(twelveHourTotalPrice).isEqualTo(13000);
    }

    @DisplayName("주간권 이용 시 합계를 계산한다.")
    @Test
    void calculateTotalPriceForWeeklyPass() {
        //given
        StudyCafeSeatPass oneWeekSeatPass = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 1, 60000, 0.0);
        StudyCafeSeatPass twoWeekSeatPass = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 2, 100000, 0.1);
        StudyCafeSeatPass threeWeekSeatPass = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 3, 130000, 0.1);
        StudyCafeSeatPass fourWeekSeatPass = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 4, 150000, 0.1);
        StudyCafeSeatPass twelveWeekSeatPass = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 12, 400000, 0.15);
        StudyCafeLockerPass lockerPass = null;

        StudyCafePassOrder oneWeekOrder = new StudyCafePassOrder(oneWeekSeatPass, lockerPass);
        StudyCafePassOrder twoWeekOrder = new StudyCafePassOrder(twoWeekSeatPass, lockerPass);
        StudyCafePassOrder threeWeekOrder = new StudyCafePassOrder(threeWeekSeatPass, lockerPass);
        StudyCafePassOrder fourWeekOrder = new StudyCafePassOrder(fourWeekSeatPass, lockerPass);
        StudyCafePassOrder twelveWeekOrder = new StudyCafePassOrder(twelveWeekSeatPass, lockerPass);

        //when
        int oneWeekTotalPrice = oneWeekOrder.getTotalPrice();
        int twoWeekTotalPrice = twoWeekOrder.getTotalPrice();
        int threeWeekTotalPrice = threeWeekOrder.getTotalPrice();
        int fourWeekTotalPrice = fourWeekOrder.getTotalPrice();
        int twelveWeekTotalPrice = twelveWeekOrder.getTotalPrice();

        //then
        assertThat(oneWeekTotalPrice).isEqualTo(60000);
        assertThat(twoWeekTotalPrice).isEqualTo(90000);
        assertThat(threeWeekTotalPrice).isEqualTo(117000);
        assertThat(fourWeekTotalPrice).isEqualTo(135000);
        assertThat(twelveWeekTotalPrice).isEqualTo(340000);
    }

    @DisplayName("고정권 이용 시 합계를 계산한다.")
    @Test
    void calculateTotalPriceForFixedPass() {
        //given
        StudyCafeSeatPass fourWeekSeatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 250000, 0.1);
        StudyCafeSeatPass twelveWeekSeatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, 700000, 0.15);
        StudyCafeLockerPass lockerPass = null;

        StudyCafePassOrder fourWeekOrder = new StudyCafePassOrder(fourWeekSeatPass, lockerPass);
        StudyCafePassOrder twelveWeekOrder = new StudyCafePassOrder(twelveWeekSeatPass, lockerPass);

        //when
        int fourWeekTotalPrice = fourWeekOrder.getTotalPrice();
        int twelveWeekTotalPrice = twelveWeekOrder.getTotalPrice();

        //then
        assertThat(fourWeekTotalPrice).isEqualTo(225000);
        assertThat(twelveWeekTotalPrice).isEqualTo(595000);
    }

    @DisplayName("고정권 이용 시 사물함을 선택했을 때 합계를 계산한다.")
    @Test
    void calculateTotalPriceWithLockerForFixedPass() {
        //given
        StudyCafeSeatPass fourWeekSeatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 250000, 0.1);
        StudyCafeSeatPass twelveWeekSeatPass = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 12, 700000, 0.15);
        StudyCafeLockerPass fourWeekLockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 4, 10000);
        StudyCafeLockerPass twelveWeekLockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 12, 30000);

        StudyCafePassOrder fourWeekOrder = new StudyCafePassOrder(fourWeekSeatPass, fourWeekLockerPass);
        StudyCafePassOrder twelveWeekOrder = new StudyCafePassOrder(twelveWeekSeatPass, twelveWeekLockerPass);

        //when
        int fourWeekTotalPrice = fourWeekOrder.getTotalPrice();
        int twelveWeekTotalPrice = twelveWeekOrder.getTotalPrice();

        //then
        assertThat(fourWeekTotalPrice).isEqualTo(235000);
        assertThat(twelveWeekTotalPrice).isEqualTo(625000);
    }

}
