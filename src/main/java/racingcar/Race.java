package racingcar;

public class Race {

    private final Car[] cars;
    private int count;

    private Race(Car[] cars, int count) {
        this.cars = cars;
        this.count = count;
    }

    public static Race of(Car[] cars, int count) {
        return new Race(cars, count);
    }

    public int getCount() {
        return this.count;
    }
}
