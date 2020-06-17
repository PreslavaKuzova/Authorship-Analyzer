package services;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextCalculator {

    private Collection<String> cleanStringCollection;
    private Collection<String> cleanSentenceCollection;

    public TextCalculator(Collection<String> cleanStringCollection,
                          Collection<String> cleanSentenceCollection) {
        this.cleanStringCollection = cleanStringCollection;
        this.cleanSentenceCollection = cleanSentenceCollection;
    }

    double calculateAverageWordLength() {
        Stream<String> stream = this.cleanStringCollection.stream();

        return stream
                .mapToInt(String::length)
                .average()
                .getAsDouble();

    }

    double calculateTypeTokenRatio() {
        Stream<String> stream = this.cleanStringCollection.stream();
        int totalNumberOfWords = getTotalNumberOfWords();

        return (double) stream.distinct().count() / totalNumberOfWords;
    }

    double calculateHapaxLegomenaRatio() {
        final int maxAllowedOccurrence = 1;
        int totalNumberOfWords = getTotalNumberOfWords();

        Stream<String> stream = this.cleanStringCollection.stream();

        Map<String, Long> occurrences = stream
                .collect(Collectors.groupingBy(Function.<String>identity(), Collectors.counting()));

        occurrences.entrySet().removeIf(entry -> entry.getValue() > maxAllowedOccurrence);

        return (double) occurrences.size() / totalNumberOfWords;
    }

    double calculateAverageSentenceLength() {
        return (double) getTotalNumberOfWords() / getTotalNumberOfSentences();
    }

    double calculateSentenceComplexity() {
        Stream<String> stream = this.cleanSentenceCollection.stream();

        long numberOfPhrases = stream
                .filter(s -> s.matches(".*[\\,\\:\\;].*"))
                .map(s -> s.split("[,|\\:|\\;]"))
                .flatMap(Arrays::stream)
                .count();

        return (double) numberOfPhrases / getTotalNumberOfSentences();
    }

    int getTotalNumberOfWords() {
        return this.cleanStringCollection.size();
    }

    int getTotalNumberOfSentences() {
        return this.cleanSentenceCollection.size();
    }

}
