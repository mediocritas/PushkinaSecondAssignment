package test.counter;

import api.EcoImpactResponseBody;
import com.google.gson.Gson;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Route;


public abstract class BaseCounterTest {

    public static void mockImpactData(Page page, String CO2, String energy, String water, String pineYears) {
        EcoImpactResponseBody.ImpactData impactData = EcoImpactResponseBody.ImpactData.builder()
                .co2(CO2)
                .energy(energy)
                .water(water)
                .pineYears(pineYears)
                .build();

        mockCountersResponse(page, createEcoImpactResponseBody(impactData));
    }

    private static EcoImpactResponseBody createEcoImpactResponseBody(EcoImpactResponseBody.ImpactData impactData) {
        EcoImpactResponseBody.PersonalImpact personalImpact = EcoImpactResponseBody.PersonalImpact.builder()
                .data(impactData)
                .build();

        EcoImpactResponseBody.Blocks blocks = EcoImpactResponseBody.Blocks.builder()
                .personalImpact(personalImpact)
                .build();

        EcoImpactResponseBody.Result result = EcoImpactResponseBody.Result.builder()
                .blocks(blocks)
                .build();

        return EcoImpactResponseBody.builder()
                .result(result)
                .isAuthorized(true)
                .build();
    }

    public static void mockCO2Counter(Page page, String CO2) {
        mockImpactData(page, CO2, "0", "0", "0");
    }

    public static void mockEnergyCounter(Page page, String energy) {
        mockImpactData(page,"0", energy, "0", "0");
    }


    public static void mockWaterCounter(Page page, String water) {
        mockImpactData(page, "0", "0", water, "0");
    }

    public static void mockPineYearsCounter(Page page, String pineYears) {
        mockImpactData(page,"0", "0", "0", pineYears);
    }

    public static void mockCountersResponse(Page page, EcoImpactResponseBody responseBody) {
        page.route("**/web/1/charity/ecoImpact/init", route -> {
            route.fulfill(new Route.FulfillOptions()
                    .setStatus(200)
                    .setContentType("application/json")
                    .setBody(new Gson().toJson(responseBody)));
        });
    }

}