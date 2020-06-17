package services;

import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AuthorshipAnalyzer {

    private Map<String, LinguisticSignature> knownSignatures;

    public AuthorshipAnalyzer(Stream<String> signaturesDataset, double[] weights) {
        this.parseKnownSignatures(signaturesDataset);

        int i = 0;
        for (FeatureType feature : FeatureType.values()) {
            feature.setWeight(weights[i]);
            i++;
        }
    }

    public String findAuthor(Stream<String> text) {
        LinguisticSignature mysteryTextSignature = calculateSignature(text);

        double bestSimilarityRatio = Double.MAX_VALUE;
        String bestSimilarityAuthor = null;

        for (Map.Entry<String, LinguisticSignature> entry : this.knownSignatures.entrySet()) {
            double currentSimilarity = calculateSimilarity(mysteryTextSignature, entry.getValue());
            if (Double.compare(currentSimilarity, bestSimilarityRatio) < 0) {
                bestSimilarityRatio = currentSimilarity;
                bestSimilarityAuthor = entry.getKey();
            }
        }

        return bestSimilarityAuthor;
    }

    public double findSimilarity(Stream<String> firstText, Stream<String> secondText) {
        LinguisticSignature first = this.calculateSignature(firstText);
        LinguisticSignature second = this.calculateSignature(secondText);

        return calculateSimilarity(first, second);
    }

    private LinguisticSignature calculateSignature(Stream<String> text) {
        TextStripper stripper = new TextStripper();

        Collection<String> cleanStringCollection = stripper.generateCleanStringCollection(text);
        Collection<String> cleanSentenceCollection = stripper.generateCleanSentenceCollection(text);

        TextCalculator calculator = new TextCalculator(cleanStringCollection, cleanSentenceCollection);

        Map<FeatureType, Double> features = new HashMap<>();

        features.put(FeatureType.AVERAGE_WORD_LENGTH, calculator.calculateAverageWordLength());
        features.put(FeatureType.TYPE_TOKEN_RATIO, calculator.calculateTypeTokenRatio());
        features.put(FeatureType.HAPAX_LEGOMENA_RATIO, calculator.calculateHapaxLegomenaRatio());
        features.put(FeatureType.AVERAGE_SENTENCE_LENGTH, calculator.calculateAverageSentenceLength());
        features.put(FeatureType.AVERAGE_SENTENCE_COMPLEXITY, calculator.calculateSentenceComplexity());

        return new LinguisticSignature(features);
    }

    private double calculateSimilarity(LinguisticSignature firstSignature, LinguisticSignature secondSignature) {

        Map<FeatureType, Double> firstFeatures = firstSignature.getFeatures();
        Map<FeatureType, Double> secondFeatures = secondSignature.getFeatures();

        double result = EnumSet.allOf(FeatureType.class)
                .stream()
                .mapToDouble(feature ->
                        Math.abs(firstFeatures.get(feature) - secondFeatures.get(feature)) * feature.getWeight())
                .sum();

        return result;
    }

    private void parseKnownSignatures(Stream<String> signatures) {
        this.knownSignatures = signatures
                .collect(Collectors.toMap(LinguisticSignature::extractAuthorName,
                        LinguisticSignature::createLinguisticSignature));
    }

}