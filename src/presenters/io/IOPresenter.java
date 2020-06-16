package presenters.io;

import controllers.io.IOController;
import presenters.Presenter;
import utils.io.devices.IODevice;

import java.io.IOException;

public class IOPresenter implements Presenter {
    private IODevice device;
    private IOController controller;

    public IOPresenter(IODevice device) {
        this.device = device;
    }

    public void setController(IOController controller) {
        this.controller = controller;
    }

   @Override
    public void readDeviceDataFlow() {
        //TODO: after the stream is read, i need to initialize the collections
    }

    @Override
    public void sendDataToDevice(String data) throws IOException {
        device.write(data);
    }

}
