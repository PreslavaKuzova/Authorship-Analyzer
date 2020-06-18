package devices.io;

import devices.Device;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public class ConsoleDevice implements Device {
    @Override
    public Collection<String> read() throws IOException {
        Scanner scanner = new Scanner(System.in);
        return Arrays.asList(scanner.nextLine());
    }

    @Override
    public void write(String data) {
        System.out.println(data);
    }
}