package devices.io;

import devices.Device;

import java.io.*;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileDevice implements Device {
    private String inputDirectory;
    private String outputDirectory;

    public FileDevice(String inputDirectory, String outputDirectory) {
        this.inputDirectory = inputDirectory;
        this.outputDirectory = outputDirectory;
    }

    @Override
    public Collection<String> read() throws IOException {
        InputStream inputStream = new FileInputStream(inputDirectory);

        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream))) {
            Collection<String> lines = buffer.lines().collect(Collectors.toList());
            return lines;
        }
    }

    @Override
    public void write(String data) throws IOException {
        OutputStream outputStream = new FileOutputStream(outputDirectory, true);

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {
            writer.write(data + System.lineSeparator());
        }
    }

}
