package test.counter;

import com.microsoft.playwright.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import page.CounterPage;
import wrapper.PlayWrightWrapper;

import java.nio.file.Paths;
import java.util.stream.Stream;

import static com.microsoft.playwright.Locator.*;

public class WaterCounterTest extends BaseCounterTest {

    private static int repetitionCounter = 0;

    @ParameterizedTest
    @MethodSource("waterValuesProvider")
    public void testWaterCounter(String waterValue) {
        PlayWrightWrapper playwrightWrapper = PlayWrightWrapper.createPageFromCommandLineArguments();
        Page page = playwrightWrapper.getPage();
        mockWaterCounter(page, waterValue);
        repetitionCounter++;
        CounterPage counterPage = new CounterPage(page);
        counterPage.navigate();
        ScreenshotOptions options = new ScreenshotOptions()
                .setPath(Paths.get("output/water_counter_screenshot" + repetitionCounter + ".png"));
        counterPage.getWaterCounter().screenshot(options);
        playwrightWrapper.close();
    }

    public static Stream<Arguments> waterValuesProvider() {
        return Stream.of(
                Arguments.of("0.49"),
                Arguments.of("0.5"),
                Arguments.of("999"),
                Arguments.of("1000"),
                Arguments.of("1001")
        );
    }
}