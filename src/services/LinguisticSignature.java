package services;

import java.util.HashMap;
import java.util.Map;

public class LinguisticSignature {
    private Map<FeatureType, Double> features;

    public LinguisticSignature(Map<FeatureType, Double> features) {
        this.features = features;
    }

    public static LinguisticSignature createLinguisticSignature(String line) {
        Map<FeatureType, Double> features = new HashMap<>();
        String[] tokens = line.split(",");

        for (FeatureType type : FeatureType.values()) {
            features.put(type, Double.parseDouble(tokens[type.getIndex()]));
        }

        return new LinguisticSignature(features);
    }

    public static String extractAuthorName(String line) {
        final int AUTHOR = 0;
        String[] tokens = line.split(",");
        return tokens[AUTHOR];
    }

    public Map<FeatureType, Double> getFeatures() {
        return this.features;
    }

}
