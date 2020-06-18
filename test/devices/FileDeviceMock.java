package devices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class FileDeviceMock implements Device {

    private String text;

    public FileDeviceMock(String text) {
        this.text = text;
    }

    @Override
    public Collection<String> read() throws IOException {
        Collection<String> collection = new ArrayList<>();
        collection.add(this.text);
        return collection;
    }

    @Override
    public void write(String data) throws IOException {
        System.out.println(text);
    }

}
