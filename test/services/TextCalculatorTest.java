package services;

import devices.Device;
import devices.io.FileDevice;
import mocks.FileDeviceMock;
import org.junit.BeforeClass;
import org.junit.Ignore;
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
        assertEquals(expected, calculator.calculateAverageWordLength(), DELTA);
    }

    @Test
    public void testCalculateAverageWordLength() throws IOException {
        initialize("mysteryText.txt");
        final int letters = 101;
        final int words = 26;
        double expected = (double) letters / words;
        assertEquals(expected, calculator.calculateAverageWordLength(), DELTA);
    }

    @Test
    public void testCalculateTypeTokenRatioWhenOnlyOneWord() throws IOException {
        this.initializeWithMock("word");
        final double expected = 1;
        assertEquals(expected, calculator.calculateTypeTokenRatio(), DELTA);
    }

    private void initializeWithMock(String testText) throws IOException {
        Device device = new FileDeviceMock(testText);
        TextStripper stripper = new TextStripper();

        Collection<String> text = device.read();

        Collection<String> cleanStringCollection = stripper.generateCleanStringCollection(text);
        Collection<String> cleanSentenceCollection = stripper.generateCleanSentenceCollection(text);

        calculator = new TextCalculator(cleanStringCollection, cleanSentenceCollection);
    }

    private void initialize(String directory) throws IOException {
        Device device = new FileDevice(directory);
        TextStripper stripper = new TextStripper();

        Collection<String> text = device.read();

        Collection<String> cleanStringCollection = stripper.generateCleanStringCollection(text);
        Collection<String> cleanSentenceCollection = stripper.generateCleanSentenceCollection(text);

        calculator = new TextCalculator(cleanStringCollection, cleanSentenceCollection);
    }
}
