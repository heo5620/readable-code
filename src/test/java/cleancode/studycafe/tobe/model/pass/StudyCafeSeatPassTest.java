package cleancode.studycafe.tobe.model.pass;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudyCafeSeatPassTest {

    @DisplayName("이용권의 할인할 가격을 계산한다.")
    @Test
    void calculateDiscountPrice() {

        //given
        StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 2, 100000, 0.1);

        //when
        int discountPrice = seatPass.getDiscountPrice();

        //then
        assertThat(discountPrice).isEqualTo(10000);
    }

}
