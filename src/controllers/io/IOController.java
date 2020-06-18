package controllers.io;

import controllers.Controller;
import initializer.AuthorshipInitializer;
import services.AuthorshipAnalyzer;

import java.util.Collection;

public class IOController implements Controller {
    private AuthorshipAnalyzer analyzer;

    public IOController() {
        AuthorshipInitializer initializer =
                new AuthorshipInitializer("knownSignatures.txt", "weights.txt");

        Collection<String> dataset = initializer.extractDataSetCollection();
        double[] weights = initializer.extractWeights();

        this.analyzer = new AuthorshipAnalyzer(dataset, weights);
    }

    public String findAuthor(Collection<String> text) {
        return analyzer.findAuthor(text);
    }

    public double findSimilarity(Collection<String> first, Collection<String> second) {
        return analyzer.findSimilarity(first, second);
    }

}
