package initializer;

import devices.Device;
import devices.io.FileDevice;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

public class AuthorshipInitializer {
    private String datasetLocation;
    private String weightsLocation;

    public AuthorshipInitializer(String datasetLocation, String weightsLocation) {
        this.datasetLocation = datasetLocation;
        this.weightsLocation = weightsLocation;
    }

    public Collection<String> extractDataSetCollection() {
        Device fileDevice = new FileDevice(datasetLocation);

        try {
            Collection<String> dataset = fileDevice.read();
            return dataset;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public double[] extractWeights() {
        Device fileDevice = new FileDevice(weightsLocation);

        try {
            Collection<String> dataset = fileDevice.read();

            double[] weights = dataset.stream()
                    .map(x -> x.split(" "))
                    .flatMap(Arrays::stream)
                    .mapToDouble(Double::parseDouble)
                    .toArray();
            return weights;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
