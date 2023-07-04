package racingcar;

import java.io.IOException;
import java.util.Arrays;

public class RaceApplication {
    public static void main(String[] args) throws IOException {
        InputView inputView = new InputView();
        Request request = inputView.init();

        Car[] cars = Arrays.stream(request.getNames())
                .map(Car::create)
                .toArray(Car[]::new);

        Race race = Race.of(cars, request.getTotalRound());

        OutputView outputView = new OutputView();

        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        for (int i = 0; i < request.getTotalRound(); i++) {
            race.play(randomNumberGenerator);
            outputView.resolve(new Response(race.getCars()));
        }

        outputView.finish(race.findWinners());
    }
}
