package services;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class TextStripper {

    private static String cleanUp(String word) {
        return word.toLowerCase()
                .replaceAll("^[!.,:;\\-?<>#\\*\'\"\\[\\(\\]\\n\\t\\\\]+|[!.,:;\\-?<>#\\*\'\"\\[\\(\\]\\n\\t\\\\]+$", "")
                .replaceAll("\\)", "");
    }

    Collection<String> generateCleanStringCollection(Collection<String> text) {
        final int minLength = 1;

        return text.stream().map(s -> s.split("\\s+"))
                .flatMap(Arrays::stream)
                .map(TextStripper::cleanUp)
                .filter(word -> word.length() >= minLength)
                .collect(Collectors.toList());
    }

    Collection<String> generateCleanSentenceCollection(Collection<String> text) {
        return Arrays.asList(
                text.stream().collect(Collectors.joining(" "))
                        .split("[\\.\\?\\!]"))
                .stream()
                .filter(x -> !(x.isBlank()))
                .collect(Collectors.toList());
    }

}
