package test.counter;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import page.CounterPage;
import wrapper.PlayWrightWrapper;

import java.nio.file.Paths;
import java.util.stream.Stream;

import static com.microsoft.playwright.Locator.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CO2CounterTest extends BaseCounterTest {

    private static int repetitionCounter = 0;

    @ParameterizedTest
    @MethodSource("CO2ValuesProvider")
    public void testCO2Counter(String CO2Value, String expectedCO2Value) {
        PlayWrightWrapper playwrightWrapper = PlayWrightWrapper.createPageFromCommandLineArguments();
        Page page = playwrightWrapper.getPage();
        mockCO2Counter(page, CO2Value);
        repetitionCounter++;
        CounterPage counterPage = new CounterPage(page);
        counterPage.navigate();
        ScreenshotOptions options = new ScreenshotOptions()
                .setPath(Paths.get("output/co2_counter_screenshot" + repetitionCounter + ".png"));
        counterPage.getWaterCounter().screenshot(options);
        PlaywrightAssertions.assertThat(counterPage.getCarbonCounter()).hasText(expectedCO2Value);
        playwrightWrapper.close();
    }

    public static Stream<Arguments> CO2ValuesProvider() {
        return Stream.of(
                Arguments.of("0.49", "0"),
                Arguments.of("0.5", "1"),
                Arguments.of("1000", "1"),
                Arguments.of("1000000", "1")
        );
    }
}
