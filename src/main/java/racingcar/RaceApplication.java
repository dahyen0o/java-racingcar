package racingcar;

import racingcar.dto.RaceRequest;
import racingcar.dto.RaceResponse;
import racingcar.service.Race;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.io.IOException;

public class RaceApplication {
    public static void main(String[] args) throws IOException {
        RaceRequest raceRequest = InputView.init();

        Race race = Race.of(raceRequest.getNames(), raceRequest.getTotalRound());

        OutputView.start();
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        for (int i = 0; i < raceRequest.getTotalRound(); i++) {
            race.play(randomNumberGenerator);
            OutputView.printRound(new RaceResponse(race.getCars()));
        }

        OutputView.finish(race.findWinners());
    }
}
