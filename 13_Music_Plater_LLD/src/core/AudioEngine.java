package core;

import devices.IAudioOutputDevice;
import models.Song;

public class AudioEngine {
    private Song currentSong ;
    private Boolean songIsPaused ;

    public AudioEngine() {
        this.currentSong = null ;
        this.songIsPaused = false ;
    }

    public String getCurrentTitle() {
        if(currentSong == null) return "" ;
        return currentSong.getTitle() ;
    }

    public Boolean isSongPaused() {
        return songIsPaused ;
    }

    public void play(IAudioOutputDevice audioOutputDevice , Song song) {
        if(song == null) throw new RuntimeException("Cannot play song null") ;
        if(song == currentSong && songIsPaused){
            songIsPaused = false ;
            System.out.println("Song Resumed") ;
            audioOutputDevice.playAudio(song) ;
            return ;
        }
        System.out.println("Song Started") ;
        audioOutputDevice.playAudio(song) ;
        currentSong = song ;
        songIsPaused = false ;
    }

    public void pause(){
        if(currentSong == null) throw new RuntimeException("Cannot pause song null") ;
        if(songIsPaused) throw new RuntimeException("Song is already paused") ;
        System.out.println("Song Paused") ;
        songIsPaused = true ;
    }
}
