package racingcar;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.domain.Car;
import racingcar.domain.Name;
import racingcar.domain.Round;
import racingcar.service.Race;
import racingcar.util.RaceUtil;

import java.util.stream.IntStream;

public class RacingCarTest {

    @Test
    void 랜덤값생성을요청했을때_성공() {
        // when
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        int randomVal = randomNumberGenerator.generate();

        // then
        assertThat(randomVal).isLessThanOrEqualTo(9);
        assertThat(randomVal).isGreaterThanOrEqualTo(0);
    }

    @Test
    void 자동차가멈추는범위의수가생성되면_정지성공() {
        // when & then
        IntStream.rangeClosed(0, 3).forEach(i -> {
            assertThat(RaceUtil.determineCarMovement(i)).isFalse();
        });

    }

    @Test
    void 자동차가전진하는범위의수가생성되면_전진성공() {
        // when & then
        IntStream.rangeClosed(4, 9).forEach(i -> {
            assertThat(RaceUtil.determineCarMovement(i)).isTrue();
        });
    }

    @Test
    void 여섯자이상의이름이들어올때_생성실패_RuntimeException발생() {
        // when & then
        assertThatThrownBy(() -> Name.from("pobiii"))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void 자동차생성할때_성공() {
        // given
        String input = "pobi";
        Car car = Car.create(input);

        // when
        int position = car.getPosition();
        String name = car.getName();

        // then
        assertThat(position).isEqualTo(0);
        assertThat(name).isEqualTo(input);
    }

    @Test
    void 자동차전진요청할때_성공() {
        // given
        Car car = Car.create("hyun");
        int beforePosition = car.getPosition();

        // when
        car.moveForward();

        // then
        assertThat(car.getPosition()).isEqualTo(beforePosition + 1);
    }

    @Test
    void 경주생성할때_성공() {
        // given
        Car[] cars = new Car[]{Car.create("pobi"), Car.create("crong"), Car.create("honux")};
        int count = 5;
        Race race = Race.of(cars, count);

        // when & then
        assertThat(race.getLeftRound()).isEqualTo(count);
    }

    @Test
    void 경주진행할때_시행횟수차감성공() {
        // given
        Car[] cars = new Car[]{Car.create("pobi"), Car.create("crong"), Car.create("honux")};
        int beforeCount = 5;
        Race race = Race.of(cars, beforeCount);

        // when
        race.play(new FixedNumberGenerator(4));

        // then
        assertThat(race.getLeftRound()).isEqualTo(beforeCount - 1);
    }

    @Test
    void 경주진행할때_자동차전진성공() {
        // given
        Car pobi = Car.create("pobi");
        int initPosition = pobi.getPosition();

        Car[] cars = new Car[]{pobi};
        Race race = Race.of(cars, 5);

        // when & then
        int round = 1;
        for (int i = 4; i < 9; i++) {
            race.play(new FixedNumberGenerator(i));
            assertThat(pobi.getPosition()).isEqualTo(initPosition + round++);
        }
    }

    @Test
    void 경주진행할때_자동차정지성공() {
        // given
        Car pobi = Car.create("pobi");
        int initPosition = pobi.getPosition();

        Car[] cars = new Car[]{pobi};
        Race race = Race.of(cars, 5);

        // when & then
        IntStream.rangeClosed(0, 3).forEach(i -> {
            race.play(new FixedNumberGenerator(i));
            assertThat(pobi.getPosition()).isEqualTo(initPosition);
        });
    }

    @Test
    void 이름이비었거나null일때_생성실패_RuntimeException발생() {
        // when & then
        assertThatThrownBy(() -> Name.from(""))
                .isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> Name.from(null))
                .isInstanceOf(RuntimeException.class);
    }

    @ParameterizedTest(name = "시도횟수가0이하일때_실패_RuntimeException발생[{arguments}]")
    @ValueSource(ints = {0, -1})
    void 시도횟수가0이하일때_실패_RuntimeException발생(int totalRound) {
        // when & then
        assertThatThrownBy(() -> Round.from(totalRound))
                .isInstanceOf(RuntimeException.class);
    }
}
