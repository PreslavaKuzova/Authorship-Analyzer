package distributors.io;

import controllers.Controller;
import controllers.io.IOController;
import devices.Device;
import distributors.Distributor;
import error_handlers.ErrorHandler;
import presenters.Presenter;
import presenters.io.IOPresenter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

public class IODistributor implements Distributor {
    private Device device;
    private Presenter presenter;
    private Controller controller;
    private ErrorHandler errorHandler;

    public IODistributor(Device device) {
        this.device = device;
        this.presenter = new IOPresenter(device);
        this.controller = new IOController();
        this.errorHandler = ErrorHandler.getInstance();
    }

    @Override
    public void start() {
        try {
            Collection<String> input = this.presenter.readDeviceDataFlow();
            findAuthor();
        } catch (IOException e) {
            errorHandler.logError(e.getStackTrace());
        }
    }

    private void findAuthor(){
        //something
    }

    private void compareTwoDataSets(){
        //something
    }
}
