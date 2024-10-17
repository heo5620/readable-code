package cleancode.studycafe.tobe.model.pass;

import cleancode.studycafe.tobe.io.provider.SeatPassFileReader;
import cleancode.studycafe.tobe.provider.SeatPassProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StudyCafeSeatPassesTest {

    @DisplayName("이용권 타입이 시간권으로 주어졌을 때, 시간권 이용권들만 찾는다.")
    @Test
    void findHourlyPass() {
        //given
        SeatPassProvider seatPassProvider = new SeatPassFileReader();
        StudyCafeSeatPasses allPasses = seatPassProvider.getSeatPasses();

        //when
        List<StudyCafeSeatPass> hourlySeatPasses = allPasses.findPassBy(StudyCafePassType.HOURLY);

        //then
        assertThat(hourlySeatPasses).allMatch(seatPass -> seatPass.getPassType() == StudyCafePassType.HOURLY);
    }

    @DisplayName("이용권 타입이 주간권으로 주어졌을 때, 시간권 이용권들만 찾는다.")
    @Test
    void findWeeklyPass() {
        //given
        SeatPassProvider seatPassProvider = new SeatPassFileReader();
        StudyCafeSeatPasses allPasses = seatPassProvider.getSeatPasses();

        //when
        List<StudyCafeSeatPass> weeklySeatPasses = allPasses.findPassBy(StudyCafePassType.WEEKLY);

        //then
        assertThat(weeklySeatPasses).allMatch(seatPass -> seatPass.getPassType() == StudyCafePassType.WEEKLY);
    }

    @DisplayName("이용권 타입이 고정권으로 주어졌을 때, 시간권 이용권들만 찾는다.")
    @Test
    void findFixedlyPass() {
        //given
        SeatPassProvider seatPassProvider = new SeatPassFileReader();
        StudyCafeSeatPasses allPasses = seatPassProvider.getSeatPasses();

        //when
        List<StudyCafeSeatPass> fixedSeatPasses = allPasses.findPassBy(StudyCafePassType.FIXED);

        //then
        assertThat(fixedSeatPasses).allMatch(seatPass -> seatPass.getPassType() == StudyCafePassType.FIXED);
    }

}
