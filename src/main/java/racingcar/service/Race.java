package racingcar.service;

import racingcar.NumberGenerator;
import racingcar.domain.Round;
import racingcar.util.RaceUtil;
import racingcar.domain.Car;

import java.util.ArrayList;
import java.util.List;

public class Race {

    private final Car[] cars;
    private final Round leftRound;

    private Race(Car[] cars, int leftRound) {
        this.cars = cars;
        this.leftRound = Round.from(leftRound);
    }

    public static Race of(Car[] cars, int count) {
        return new Race(cars, count);
    }

    public int getLeftRound() {
        return this.leftRound.getRound();
    }

    public void play(NumberGenerator numberGenerator) {
        startRound();
        for (Car car : cars) {
            doPlay(numberGenerator, car);
        }
    }

    private void doPlay(NumberGenerator numberGenerator, Car car) {
        if (RaceUtil.determineCarMovement(numberGenerator.generate())) {
            car.moveForward();
        }
    }

    public String[] findWinners() {
        int maxPosition = 0;
        for (Car car : cars) {
            maxPosition = Math.max(car.getPosition(), maxPosition);
        }

        List<String> names = new ArrayList<>();
        for (Car car : cars) {
            if (maxPosition == car.getPosition()) {
                names.add(car.getName());
            }
        }

        return names.toArray(String[]::new);
    }

    private void startRound() {
        this.leftRound.decrease();
    }

    public Car[] getCars() {
        return this.cars;
    }
}
