package racingcar;

import racingcar.domain.Car;
import racingcar.dto.RaceRequest;
import racingcar.dto.Response;
import racingcar.service.Race;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.io.IOException;
import java.util.Arrays;

public class RaceApplication {
    public static void main(String[] args) throws IOException {
        RaceRequest raceRequest = InputView.init();

        Car[] cars = Arrays.stream(raceRequest.getNames())
                .map(Car::create)
                .toArray(Car[]::new);

        Race race = Race.of(cars, raceRequest.getTotalRound());

        OutputView.start();
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        for (int i = 0; i < raceRequest.getTotalRound(); i++) {
            race.play(randomNumberGenerator);
            OutputView.printRound(new Response(race.getCars()));
        }

        OutputView.finish(race.findWinners());
    }
}
