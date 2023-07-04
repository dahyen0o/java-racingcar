package racingCar;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import racingcar.RandomUtil;

public class RacingCarTest {
    
    @Test
    void 랜덤값생성을요청했을때_성공() {
        // when
        int randomVal = RandomUtil.generate();
        
        // then
        assertThat(randomVal).isLessThanOrEqualTo(9);
        assertThat(randomVal).isGreaterThanOrEqualTo(0);
    }
}
