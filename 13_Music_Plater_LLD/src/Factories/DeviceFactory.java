package Factories;

import devices.BluetoohSpeakerAdapter;
import devices.HeadphonesAdapter;
import devices.IAudioOutputDevice;
import devices.WiredSpeakerAdapter;
import enums.DeviceType;
import external.BluetoothSpeakerAPI;
import external.HeadphonesAPI;
import external.WiredSpeakerAPI;

public class DeviceFactory {
    public static IAudioOutputDevice createDevice(DeviceType deviceType) {
        return switch (deviceType) {
            case BLUETOOTH -> new BluetoohSpeakerAdapter(new BluetoothSpeakerAPI());
            case HEADPHONES -> new HeadphonesAdapter(new HeadphonesAPI());
            default -> new WiredSpeakerAdapter(new WiredSpeakerAPI());
        };
    }
}
