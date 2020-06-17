package distributors.io;

import controllers.Controller;
import controllers.io.IOController;
import devices.Device;
import distributors.Distributor;
import presenters.Presenter;
import presenters.io.IOPresenter;

import java.io.IOException;
import java.util.stream.Stream;

//TODO: handles all exceptions, but how???
public class IODistributor implements Distributor {
    private Device device;
    private Presenter presenter;
    private Controller controller;

    public IODistributor(Device device) {
        this.device = device;
        this.presenter = new IOPresenter(device);
        this.controller = new IOController();
    }

    @Override
    public void start() {
        try {
            Stream<String> input = this.presenter.readDeviceDataFlow();
            //switch-case for the options all the options that are provided to the user
            //send that info to the controller
            //get back the result
            //send it to the presenter
            //presenters shows it to the device
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
