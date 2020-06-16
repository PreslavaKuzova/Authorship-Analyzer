package presenters;

import java.io.IOException;

public interface Presenter {
    void readDeviceDataFlow();

   void sendDataToDevice(String data) throws IOException;
}
