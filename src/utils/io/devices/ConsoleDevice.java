package utils.io.devices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class ConsoleDevice implements IODevice {
    @Override
    public Stream<String> read() throws IOException {
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in))) {
            Stream<String> lines = buffer.lines();
            return lines;
        }
    }

    @Override
    public void write(String data) {
        System.out.println(data);
    }
}
