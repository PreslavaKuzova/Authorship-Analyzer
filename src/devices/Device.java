package devices;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Stream;

public interface Device {

    Collection<String> read() throws IOException;

    void write(String data) throws IOException;

}
