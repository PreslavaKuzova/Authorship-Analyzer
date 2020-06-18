package presenters;

import java.io.IOException;
import java.util.Collection;

public interface Presenter {
    Collection<String> readDeviceDataFlow() throws IOException;

    void sendDataToDevice(String data) throws IOException;

}
