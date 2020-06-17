package devices;

import java.io.IOException;
import java.util.stream.Stream;

public interface Device {

    Stream<String> read() throws IOException;

    void write(String data) throws IOException;

}
