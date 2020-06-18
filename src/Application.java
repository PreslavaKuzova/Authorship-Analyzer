import devices.Device;
import devices.io.ConsoleDevice;
import devices.io.FileDevice;
import distributors.Distributor;
import distributors.io.IODistributor;

public class Application {
    public static void main(String[] args) {
        Device device = new ConsoleDevice();
        Distributor distributor = new IODistributor(device);
        distributor.start();
    }
}
