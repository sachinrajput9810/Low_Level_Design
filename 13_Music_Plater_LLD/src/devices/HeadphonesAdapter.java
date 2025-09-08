package devices;

import external.HeadphonesAPI;
import models.Song;

public class HeadphonesAdapter implements IAudioOutputDevice{
    private HeadphonesAPI headphonesApi ;
    public HeadphonesAdapter(HeadphonesAPI headphonesApi){
        this.headphonesApi = headphonesApi ;
    }
    @Override
    public void playAudio(Song song) {
        String payload = song.getTitle() + " by " + song.getArtist() ;
        headphonesApi.playSoundViaHeadphones(payload) ;
    }

}
