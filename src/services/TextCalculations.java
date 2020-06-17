package services;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextCalculations {

    //TODO: static methods or create constructor

    private double calculateAverageWordLength(Collection<String> cleanStringCollection) {
        Stream<String> stream = cleanStringCollection.stream();

        return stream
                .mapToInt(String::length)
                .average()
                .getAsDouble();

    }

    private double calculateTypeTokenRatio(Collection<String> cleanStringCollection) {
        Stream<String> stream = cleanStringCollection.stream();
        int totalNumberOfWords = getTotalNumberOfWords(cleanStringCollection);

        return (double) stream.distinct().count() / totalNumberOfWords;
    }

    private double calculateHapaxLegomenaRatio(Collection<String> cleanStringCollection) {
        final int maxAllowedOccurrence = 1;
        int totalNumberOfWords = getTotalNumberOfWords(cleanStringCollection);

        Stream<String> stream = cleanStringCollection.stream();

        Map<String, Long> occurrences = stream
                .collect(Collectors.groupingBy(Function.<String>identity(), Collectors.counting()));

        occurrences.entrySet().removeIf(entry -> entry.getValue() > maxAllowedOccurrence);

        return (double) occurrences.size() / totalNumberOfWords;
    }

    private double calculateAverageSentenceLength(Collection<String> cleanStringCollection,
                                                  Collection<String> cleanSentenceCollection) {
        return (double) getTotalNumberOfWords(cleanStringCollection)
                / getTotalNumberOfSentences(cleanSentenceCollection);
    }

    private double calculateSentenceComplexity(Collection<String> cleanSentenceCollection) {
        Stream<String> stream = cleanSentenceCollection.stream();

        long numberOfPhrases = stream
                .filter(s -> s.matches(".*[\\,\\:\\;].*"))
                .map(s -> s.split("[,|\\:|\\;]"))
                .flatMap(Arrays::stream)
                .count();

        return (double) numberOfPhrases / getTotalNumberOfSentences(cleanSentenceCollection);
    }

    private int getTotalNumberOfWords(Collection<String> cleanStringCollection) {
        return cleanStringCollection.size();
    }

    private int getTotalNumberOfSentences(Collection<String> cleanSentenceCollection) {
        return cleanSentenceCollection.size();
    }

}
