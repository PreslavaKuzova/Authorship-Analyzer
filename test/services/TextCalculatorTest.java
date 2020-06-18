package services;

import devices.Device;
import devices.io.FileDevice;
import devices.FileDeviceMock;
import org.junit.Test;

import java.io.IOException;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class TextCalculatorTest {

    private static TextCalculator calculator;
    private final double DELTA = 0.1;

    @Test
    public void testCalculateAverageLengthWhenWordContainsSymbols() throws IOException {
        this.initializeWithMock("t'est!:");

        final double expected = 5;
        final double actual = calculator.calculateAverageWordLength();

        assertEquals(expected, actual, DELTA);
    }

    @Test
    public void testCalculateAverageWordLength() throws IOException {
        initialize("mysteryText.txt");

        final int letters = 101;
        final int words = 26;
        final double expected = (double) letters / words;

        final double actual = calculator.calculateAverageWordLength();

        assertEquals(expected, actual, DELTA);
    }

    @Test
    public void testCalculateTypeTokenRatioWhenOnlyOneWord() throws IOException {
        this.initializeWithMock("word");

        final double expected = 1;
        final double actual = calculator.calculateTypeTokenRatio();

        assertEquals(expected, actual, DELTA);
    }

    @Test
    public void testCalculateHapaxLegomenaRatioWhenOnlyOneWordRepeatsThreeTimes() throws IOException {
        this.initializeWithMock("word word word");

        final double expected = 0;
        final double actual = calculator.calculateHapaxLegomenaRatio();

        assertEquals(expected, actual, DELTA);
    }

    @Test
    public void testCalculateHapaxLegomenaRatioWhenOnlyOneWordIsUnique() throws IOException {
        this.initializeWithMock("word word unique");

        final double expected = (double) 1 / 3;
        final double actual = calculator.calculateHapaxLegomenaRatio();

        assertEquals(expected, actual, DELTA);
    }

    @Test
    public void testCalculateHapaxLegomenaRatio() throws IOException {
        initialize("mysteryText.txt");

        final int unique = 15;
        final int words = 24;

        double expected = (double) unique / words;
        double actual = calculator.calculateHapaxLegomenaRatio();

        assertEquals(expected, actual, DELTA);
    }

    @Test
    public void testCalculateSentenceComplexityWhenOnlyOneSentenceWithoutPhrases() throws IOException {
        this.initializeWithMock("This is one sentence without any phrases");

        final double expected = 0;
        final double actual = calculator.calculateSentenceComplexity();

        assertEquals(expected, actual, DELTA);
    }

    @Test
    public void testCalculateSentenceComplexityWhenTwoSentencesWithThreePhrases() throws IOException {
        this.initializeWithMock("This is the sentence, don't you agree, huh? Agreed.");

        final double expected = (double) 3 / 2;
        final double actual = calculator.calculateSentenceComplexity();

        assertEquals(expected, actual, DELTA);
    }

    @Test
    public void testCalculateSentenceComplexity() throws IOException {
        initialize("mysteryText.txt");

        final int phrases = 3;
        final int sentences = 4;

        double expected = (double) phrases / sentences;
        double actual = calculator.calculateSentenceComplexity();

        assertEquals(expected, actual, DELTA);
    }

    @Test
    public void testCalculateAverageSentenceLengthWhenTwoSentencesWithSevenWords() throws IOException {
        this.initializeWithMock("This is a sentence. So is this.");

        final double expected = (double) 7 / 2;
        final double actual = calculator.calculateAverageSentenceLength();

        assertEquals(expected, actual, DELTA);
    }

    @Test
    public void testCalculateAverageSentenceLength() throws IOException {
        initialize("mysteryText.txt");

        final int words = 26;
        final int sentences = 4;

        final double expected = (double) words / sentences;
        final double actual = calculator.calculateAverageSentenceLength();

        assertEquals(expected, actual, DELTA);
    }

    private void initialize(String directory) throws IOException {
        Device device = new FileDevice(directory);
        TextStripper stripper = new TextStripper();

        Collection<String> text = device.read();

        Collection<String> cleanStringCollection = stripper.generateCleanStringCollection(text);
        Collection<String> cleanSentenceCollection = stripper.generateCleanSentenceCollection(text);

        calculator = new TextCalculator(cleanStringCollection, cleanSentenceCollection);
    }

    private void initializeWithMock(String testText) throws IOException {
        Device device = new FileDeviceMock(testText);
        TextStripper stripper = new TextStripper();

        Collection<String> text = device.read();

        Collection<String> cleanStringCollection = stripper.generateCleanStringCollection(text);
        Collection<String> cleanSentenceCollection = stripper.generateCleanSentenceCollection(text);

        calculator = new TextCalculator(cleanStringCollection, cleanSentenceCollection);
    }
}
