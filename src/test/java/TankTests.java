import org.example.TankCalculator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TankTests {
    static Stream<Arguments> tankTestCases() {
        return Stream.of(
                Arguments.of(new int[]{4, 6}, 2, 2, 2),
                Arguments.of(new int[]{30, 3, 7, 20}, 1, 10, 30),
                Arguments.of(new int[]{100000000, 99999999, 10000000, 1000000, 900000, 90000, 9000, 800, 80, 777}, 7, 61, 14285714)
        );
    }

    @ParameterizedTest()
    @MethodSource("tankTestCases")
    void shouldCalculateOverflowTime(int[] tanks, int flowRate, int lastTankOverflowInSeconds, int maxTankOverflowTimeInSeconds) {

        var calculator = new TankCalculator(tanks, flowRate);

        var result = calculator.doCalculation();

        assertEquals(result.LastTankOverflowInSeconds, lastTankOverflowInSeconds);
        assertEquals(result.MaxTankOverflowTimeInSeconds, maxTankOverflowTimeInSeconds);
    }
}