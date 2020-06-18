package distributors.io;

import controllers.Controller;
import controllers.io.IOController;
import devices.Device;
import distributors.Distributor;
import error_handlers.ErrorHandler;
import presenters.Presenter;
import presenters.io.IOPresenter;

import java.io.IOException;
import java.util.Collection;

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
            this.presenter.sendDataToDevice(findAuthor(input));
        } catch (IOException e) {
            errorHandler.logError(e.getStackTrace());
        }
    }

    private String findAuthor(Collection<String> input) {
        return this.controller.findAuthor(input);
    }

    private double compareTwoDataSets(Collection<String> first, Collection<String> second) {
        return this.controller.findSimilarity(first, second);
    }
}
