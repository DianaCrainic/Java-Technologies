package com.uaic.lab2.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeviceFactory {
    private List<Device> devices;

    public DeviceFactory() {
        this.devices = new ArrayList<>(Arrays.asList(
                new Device("input", "keyboards", "100"),
                new Device("input", "mouse", "101"),
                new Device("output", "monitor", "200")));
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void addDevice(Device device) {
        devices.add(device);
    }

    //    public List<Device> getDevices() {
//        return Arrays.asList(new Device("input", "keyboards", 100),
//                new Device("input", "mouse", 101),
//                new Device("output", "monitor", 200));
//    }

}
