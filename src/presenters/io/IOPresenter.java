package presenters.io;

import devices.Device;
import presenters.Presenter;

import java.io.IOException;
import java.util.Collection;

public class IOPresenter implements Presenter {
    private Device device;

    public IOPresenter(Device device) {
        this.device = device;
    }

    @Override
    public Collection<String> readDeviceDataFlow() throws IOException {
        Collection<String> input = this.device.read();
        return input;
    }

    @Override
    public void sendDataToDevice(String data) throws IOException {
        device.write(data);
    }


}
