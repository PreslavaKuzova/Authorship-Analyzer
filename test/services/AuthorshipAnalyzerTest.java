package services;

import devices.Device;
import devices.io.FileDevice;
import initializer.AuthorshipInitializer;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class AuthorshipAnalyzerTest {

    private static AuthorshipAnalyzer analyzer;

    @BeforeClass
    public static void initialize() {
        AuthorshipInitializer initializer =
                new AuthorshipInitializer("knownSignatures.txt", "weights.txt");

        Collection<String> dataset = initializer.extractDataSetCollection();
        double[] weights = initializer.extractWeights();

        analyzer = new AuthorshipAnalyzer(dataset, weights);
    }

    @Test
    public void testFindingAnAuthorWhenAuthorIsBrothersGrim() throws IOException {
        final String expected = "Brothers Grim";
        Device device = new FileDevice("BrothersGrim.txt");
        Collection<String> mysteryText = device.read();
        assertEquals(expected, analyzer.findAuthor(mysteryText));
    }
}
