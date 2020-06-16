package controllers.io;

import presenters.io.IOPresenter;
import utils.io.devices.IODevice;

public class IOController {
    private IOPresenter presenter;

    //TODO: shano, think of something else?
    public IOController(IODevice device) {
        this.presenter = new IOPresenter(device);
        this.presenter.setController(this);
    }
}
