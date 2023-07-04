package racingcar;

public class OutputView {
    public void resolve(Response response) {
        for (CarVO carVO : response.getCars()) {
            System.out.printf("%s : %s%n", carVO.getName(), carVO.getPosition());
        }
        System.out.println();
    }

    public void finish(String[] winners) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < winners.length - 1; i++) {
            stringBuilder.append(winners[i]).append(',');
        }
        stringBuilder.append(winners[winners.length - 1]);

        System.out.printf("%s가 최종 우승했습니다.", stringBuilder);
    }
}
