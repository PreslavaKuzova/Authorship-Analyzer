package services;

import devices.Device;
import devices.io.FileDevice;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class TextCalculatorTest {

    private static TextCalculator calculator;
    private final double DELTA = 0.1;

    @BeforeClass
    public static void initialize() throws IOException {
        Device device = new FileDevice("mysteryText.txt");
        TextStripper stripper = new TextStripper();

        Collection<String> text = device.read();

        Collection<String> cleanStringCollection = stripper.generateCleanStringCollection(text);
        Collection<String> cleanSentenceCollection = stripper.generateCleanSentenceCollection(text);

        calculator = new TextCalculator(cleanStringCollection, cleanSentenceCollection);
    }

    @Ignore
    @Test
    public void testCalculateAverageLengthWhenWordContainsSymbols() throws IOException {
        final double expected = 5;
        assertEquals(5, calculator.calculateAverageWordLength(), DELTA);
    }

    @Test
    public void testCalculateAverageWordLength() {
        final int letters = 101;
        final int words = 26;
        double expected = (double) letters / words;
        assertEquals(expected, calculator.calculateAverageWordLength(), DELTA);
    }
    
}
