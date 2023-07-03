package calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {
    @Test
    void 기본구분자만포함했을때_계산성공() {
        // given
        String input1 = "";
        String input2 = "1,2";
        String input3 = "1,2,3";
        String input4 = "1,2:3";

        // when
        int result1 = Calculator.sum(input1);
        int result2 = Calculator.sum(input2);
        int result3 = Calculator.sum(input3);
        int result4 = Calculator.sum(input4);

        // then
        assertEquals(0, result1);
        assertEquals(3, result2);
        assertEquals(6, result3);
        assertEquals(6, result4);
    }

    @Test
    void 커스텀구분자만포함할때_계산성공() {
        // given
        String input1 = "//;\n1;2;3";

        // when
        int result1 = Calculator.sum(input1);

        // then
        assertEquals(6, result1);
    }

    @Test
    void 숫자가아닌문자를포함할때_계산실패_RuntimeException발생() {
        // given
        String input1 = "hello";

        // when & then
        assertThrows(RuntimeException.class, () -> Calculator.sum(input1));
    }

    @Test
    void 음수를포함할때_계산실패_RuntimeException발생() {
        // given
        String input1 = "-1,2,3";

        // when & then
        assertThrows(RuntimeException.class, () -> Calculator.sum(input1));
    }
}
