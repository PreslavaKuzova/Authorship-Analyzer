package devices.io;

import devices.Device;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.stream.Stream;

public class FileDevice implements Device {
    private String inputDirectory;
    private String outputDirectory;

    public FileDevice(String inputDirectory, String outputDirectory) {
        this.inputDirectory = inputDirectory;
        this.outputDirectory = outputDirectory;
    }

    @Override
    public Stream<String> read() throws IOException {
        InputStream inputStream = new FileInputStream(inputDirectory);

        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream))) {
            Stream<String> lines = buffer.lines();
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
