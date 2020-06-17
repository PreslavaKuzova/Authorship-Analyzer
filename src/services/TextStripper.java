package services;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextStripper {

    Collection<String> generateCleanStringCollection(Stream<String> text) {
        final int minLength = 1;

        return text.map(s -> s.split("\\s+"))
                .flatMap(Arrays::stream)
                .map(TextStripper::cleanUp)
                .filter(word -> word.length() >= minLength)
                .collect(Collectors.toList());
    }

    private static String cleanUp(String word) {
        return word.toLowerCase()
                .replaceAll("^[!.,:;\\-?<>#\\*\'\"\\[\\(\\]\\n\\t\\\\]+|[!.,:;\\-?<>#\\*\'\"\\[\\(\\]\\n\\t\\\\]+$", "")
                .replaceAll("\\)", "");
    }

    Collection<String> generateCleanSentenceCollection(Stream<String> text) {
        return Arrays.asList(
                text.collect(Collectors.joining(" "))
                        .split("[\\.\\?\\!]"))
                .stream()
                .filter(x -> !(x.isBlank()))
                .collect(Collectors.toList());
    }

}