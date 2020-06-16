package presenters.io;

import controllers.io.IOController;
import presenters.Presenter;
import utils.io.devices.IODevice;

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
    public void startReadingDataFlow() {

    }

    @Override
    public void sendDataToDevice() {

    }

}
