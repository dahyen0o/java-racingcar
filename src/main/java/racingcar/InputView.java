package racingcar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public Request init() throws IOException {
        String names = inputNames();
        String totalRound = inputTotalRound();

        return new Request(names, totalRound);
    }

    private String inputTotalRound() throws IOException {
        return br.readLine();
    }

    private String inputNames() throws IOException {
        return br.readLine();
    }
}
