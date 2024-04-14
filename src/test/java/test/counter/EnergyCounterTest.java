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

public class EnergyCounterTest extends BaseCounterTest {

    private static int repetitionCounter = 0;

    @ParameterizedTest
    @MethodSource("energyValuesProvider")
    public void testEnergyCounter(String energyValue, String expectedEnergyValue) {
        PlayWrightWrapper playwrightWrapper = PlayWrightWrapper.createPageFromCommandLineArguments();
        Page page = playwrightWrapper.getPage();
        mockEnergyCounter(page, energyValue);
        repetitionCounter++;
        CounterPage counterPage = new CounterPage(page);
        counterPage.navigate();
        ScreenshotOptions options = new ScreenshotOptions()
                .setPath(Paths.get("output/energy_counter_screenshot" + repetitionCounter + ".png"));
        counterPage.getWaterCounter().screenshot(options);
        PlaywrightAssertions.assertThat(counterPage.getEnergyCounter()).hasText(expectedEnergyValue);
        playwrightWrapper.close();
    }

    public static Stream<Arguments> energyValuesProvider() {
        return Stream.of(
                Arguments.of("0.49", "0"),
                Arguments.of("0.5", "1"),
                Arguments.of("1000", "1"),
                Arguments.of("1001", "1")
        );
    }
}
