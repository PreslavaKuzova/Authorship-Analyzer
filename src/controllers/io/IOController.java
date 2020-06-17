package controllers.io;

import controllers.Controller;
import services.AuthorshipAnalyzer;

import java.util.stream.Stream;

public class IOController implements Controller {
    AuthorshipAnalyzer analyzer;

    //not optimal, should be in the constructor, but i shouldn't pass anything to the constructor???
    public void setAnalyzer(Stream<String> signaturesDataset, double[] weights) {
        this.analyzer = new AuthorshipAnalyzer(signaturesDataset, weights);
    }

    public String findAuthor(Stream<String> text) {
        return analyzer.findAuthor(text);
    }

    public double findSimilarity(Stream<String> first, Stream<String> second) {
        return analyzer.findSimilarity(first, second);
    }

}
