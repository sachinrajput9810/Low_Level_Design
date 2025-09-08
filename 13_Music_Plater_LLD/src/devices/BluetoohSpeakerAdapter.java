package devices;

import external.BluetoothSpeakerAPI;
import models.Song;

public class BluetoohSpeakerAdapter implements  IAudioOutputDevice{
    private BluetoothSpeakerAPI bluetoohSpeakerAPI ;
    public BluetoohSpeakerAdapter(BluetoothSpeakerAPI bluetoohSpeakerAPI){
        this.bluetoohSpeakerAPI = bluetoohSpeakerAPI ;
    }
    @Override
    public void playAudio(Song song) {
        String payload = song.getTitle() + " by " + song.getArtist() ;
        bluetoohSpeakerAPI.playSoundViaBluetooth(payload) ;
    }
}
