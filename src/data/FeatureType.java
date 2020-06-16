package data;

public enum FeatureType {
    AVERAGE_WORD_LENGTH(0),
    TYPE_TOKEN_RATIO(1),
    HAPAX_LEGOMENA_RATIO(2),
    AVERAGE_SENTENCE_LENGTH(3),
    AVERAGE_SENTENCE_COMPLEXITY(4);

    private double weight;
    private int index;

    FeatureType(int index) {
        this.weight = 0;
        this.index = index;
    }

    double getWeight() {
        return this.weight;
    }

    void setWeight(double weight) {
        this.weight = weight;
    }

    public int getIndex() {
        return index;
    }
}
