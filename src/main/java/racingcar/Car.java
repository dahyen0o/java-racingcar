package racingcar;

public class Car {
    private final String name;
    private final int position;

    private Car(String name) {
        this(name, 0);
    }

    private Car(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public static Car from(String name) {
        return new Car(name);
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
