package presenters;

import java.io.IOException;
import java.util.stream.Stream;

public interface Presenter {
    Stream<String> readDeviceDataFlow() throws IOException;

   void sendDataToDevice(String data) throws IOException;

}
