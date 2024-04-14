package wrapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import com.microsoft.playwright.*;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayWrightWrapper {
    Page page;
    Browser browser;
    BrowserContext browserContext;

    public static PlayWrightWrapper createPageFromCommandLineArguments() {
        String browserTypeArg = System.getProperty("browserType", "chromium");
        int width = Integer.parseInt(System.getProperty("width", "1920"));
        int height = Integer.parseInt(System.getProperty("height", "1080"));

        Playwright playwright = Playwright.create();
        BrowserType browserType;
        switch (browserTypeArg) {
            case "firefox":
                browserType = playwright.firefox();
                break;
            case "webkit":
                browserType = playwright.webkit();
                break;
            case "chromium":
            default:
                browserType = playwright.chromium();
                break;
        };

        Browser browser = browserType.launch(
                new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(100)
        );
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(width, height));
        Page page = browserContext.newPage();
        return PlayWrightWrapper.builder()
                .page(page)
                .browser(browser)
                .browserContext(browserContext)
                .build();
    }

    public void close() {
        page.close();
        browserContext.close();
        browser.close();
    }
}