package page;

import com.microsoft.playwright.*;

import lombok.Data;

@Data
public class CounterPage {
    private final Page page;
    private final Locator waterCounter;
    private final Locator energyCounter;
    private final Locator carbonCounter;

    public CounterPage(Page page) {
        this.page = page;
        this.waterCounter = page.locator("//*[@id=\"app\"]/div/div[3]/div/div/div/div/div[3]/div/div[2]/div[4]/div/div[1]");
        this.energyCounter = page.locator("//*[@id=\"app\"]/div/div[3]/div/div/div/div/div[3]/div/div[2]/div[6]/div/div[1]");
        this.carbonCounter = page.locator("//*[@id=\"app\"]/div/div[3]/div/div/div/div/div[3]/div/div[2]/div[2]/div/div[1]");
    }

    public void navigate() {
        page.navigate("https://www.avito.ru/avito-care/eco-impact");
    }

    public static void isWaterCounterExist() {
    }

}