package utils.io.devices;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.stream.Stream;

public interface IODevice {

    //TODO: maybe make that a default method
    Stream<String> read() throws IOException;

    void write(String data) throws IOException;
}
