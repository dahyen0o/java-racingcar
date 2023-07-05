package racingcar.domain;

public class Car {
    private final Name name;
    private final Position position;

    private Car(String name) {
        this(name, 0);
    }

    private Car(String name, int position) {
        this.name = Name.from(name);
        this.position = Position.from(position);
    }

    public static Car create(String name) {
        return new Car(name);
    }

    public String getName() {
        return name.getName();
    }

    public int getPosition() {
        return position.getPosition();
    }

    public void moveForward() {
        this.position.increase();
    }
}
