package api;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EcoImpactResponseBody {
    private Result result;
    private boolean isAuthorized;

    @Data
    @Builder
    public static class Result {
        private Blocks blocks;
    }

    @Data
    @Builder
    public static class Blocks {
        private PersonalImpact personalImpact;
    }

    @Data
    @Builder
    public static class PersonalImpact {
        private String avatarUrl;
        private ImpactData data;
    }

    @Builder
    @Data
    public static class ImpactData {
        String co2;
        String energy;
        String water;
        String pineYears;

    }

}