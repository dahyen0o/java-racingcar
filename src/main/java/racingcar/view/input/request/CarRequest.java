package racingcar.view.input.request;

import racingcar.domain.car.CarResource;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CarRequest {

    private final static String DELIMITER = ",";
    private final String cars;

    public CarRequest(String cars) {
        this.cars = cars;
    }

    public CarResource toResource(){
        return CarResource.from(
                Arrays.stream(cars.split(DELIMITER))
                        .collect(Collectors.toList())
        );
    }
}
