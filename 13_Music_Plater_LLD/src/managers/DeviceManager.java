package managers;

import Factories.DeviceFactory;
import devices.IAudioOutputDevice;
import enums.DeviceType;

public class DeviceManager {
    private static DeviceManager instance = null;
    private IAudioOutputDevice currentOutputDevice ;

    private DeviceManager(){
        currentOutputDevice = null ;
    }
    public static synchronized DeviceManager getInstance(){
        if(instance == null){
            instance = new DeviceManager() ;
        }
        return instance ;
    }

    public void connectDevice(DeviceType deviceType){
        currentOutputDevice = DeviceFactory.createDevice(deviceType) ;
        switch (deviceType){
            case BLUETOOTH:
                System.out.println("Connected to bluetooth speaker") ;
                break ;
            case HEADPHONES:
                System.out.println("Connected to headphones") ;
                break ;
            case WIRED:
                System.out.println("Connected to wired speaker") ;
                break ;
        }
    }

    public IAudioOutputDevice getCurrentOutputDevice() {
        if(currentOutputDevice == null) throw new RuntimeException("No device connected") ;
        return currentOutputDevice ;
    }

    public Boolean hasOutputDevice(){
        return currentOutputDevice != null ;
    }

}
