import org.example.TankCalculator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TankTests {
    static Stream<Arguments> tankTestCases() {
        return Stream.of(
                Arguments.of(new String[]{"2 2", "4", "6"}, "2 2"),
                Arguments.of(new String[]{"4 1", "30", "3", "7", "20"}, "10 30"),
                Arguments.of(new String[]{"10 7", "100000000", "99999999", "10000000", "1000000", "900000", "90000", "9000", "800", "80", "777"}, "61 14285714")
        );
    }

    @ParameterizedTest()
    @MethodSource("tankTestCases")
    void shouldCalculateOverflowTime(String[] inputs, String output) {

        var calculator = new TankCalculator(inputs);

        var result = calculator.doCalculation();

        assertEquals(output, result);
    }
}