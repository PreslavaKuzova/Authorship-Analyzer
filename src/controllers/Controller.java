package controllers;

import java.util.Collection;

public interface Controller {
    public String findAuthor(Collection<String> text);

    public double findSimilarity(Collection<String> first, Collection<String> second);
}
