package data;

import java.io.IOException;
import java.io.InputStream;

public interface AuthorshipAnalyzer {
    LinguisticSignature calculateSignature(InputStream mysteryText) throws IOException;

    double calculateSimilarity(LinguisticSignature firstSignature, LinguisticSignature secondSignature);

    String findAuthor(InputStream mysteryText) throws IOException;
}