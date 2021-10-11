package com.uaic.lab2.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeviceFactory {
    private final List<Device> devices;

    public DeviceFactory() {
        this.devices = new ArrayList<>(Arrays.asList(
                new Device(Device.CategoryType.INPUT, "keyboard", "mechanical keyboard"),
                new Device(Device.CategoryType.INPUT, "mouse", "silence mouse"),
                new Device(Device.CategoryType.OUTPUT, "monitor", "HD monitor")));
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void addDevice(Device device) {
        devices.add(device);
    }
}
