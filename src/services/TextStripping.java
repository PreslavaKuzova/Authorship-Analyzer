package services;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextStripping {

    public static String cleanUp(String word) {
        return word.toLowerCase()
                .replaceAll("^[!.,:;\\-?<>#\\*\'\"\\[\\(\\]\\n\\t\\\\]+|[!.,:;\\-?<>#\\*\'\"\\[\\(\\]\\n\\t\\\\]+$", "")
                .replaceAll("\\)", "");
    }

    private static Collection<String> generateCleanStringCollection(Stream<String> text) throws IOException {
        final int minLength = 1;

        return text.map(s -> s.split("\\s+"))
                .flatMap(Arrays::stream)
                .map(TextStripping::cleanUp)
                .filter(word -> word.length() >= minLength)
                .collect(Collectors.toList());
    }

    private static Collection<String> generateCleanSentenceCollection(Stream<String> text) throws IOException {
        return Arrays.asList(
                text.collect(Collectors.joining(" "))
                        .split("[\\.\\?\\!]"))
                .stream()
                .filter(x -> !(x.isBlank()))
                .collect(Collectors.toList());
    }

}
