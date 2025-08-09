import core.AudioEngine;
import devices.IAudioOutputDevice;
import enums.DeviceType;
import enums.PlayingStrategyType;
import managers.DeviceManager;
import managers.PlaylistManager;
import managers.StrategyManager;
import models.PlayList;
import models.Song;
import strategies.PlayStrategy;

public class MusicPlayerFascade {
    private static MusicPlayerFascade instance  = null ;
    private AudioEngine audioEngine ;
    private PlayList loadedPlaylist ;
    private PlayStrategy playStrategy ;

    private MusicPlayerFascade() {
        audioEngine = new AudioEngine() ;
        loadedPlaylist = null ;
        playStrategy = null ;
    }

    public static synchronized MusicPlayerFascade getInstance() {
        if(instance == null) instance = new MusicPlayerFascade() ;
        return instance ;
    }

    public void connect(DeviceType deviceType) {
        DeviceManager.getInstance().connectDevice(deviceType) ;
    }

    public void setPlayStrategy(PlayingStrategyType playingStrategyType) {
        this.playStrategy = StrategyManager.getInstance().getStrategy(playingStrategyType) ;
    }

    public void loadPlaylist(String name) {
        loadedPlaylist = PlaylistManager.getInstance().getPlayList(name) ;
        if(playStrategy == null){
            throw new RuntimeException("PlayStrategy is not set") ;
        }
        playStrategy.setPlaylist(loadedPlaylist) ;
    }

    public void playSong(Song song){
        if(!DeviceManager.getInstance().hasOutputDevice()) throw new RuntimeException("No device connected") ;
        IAudioOutputDevice audioOutputDevice = DeviceManager.getInstance().getCurrentOutputDevice() ;
        audioEngine.play(audioOutputDevice , song) ;
    }

    public void pauseSong(Song song) {
        if(!audioEngine.getCurrentTitle().equals(song.getTitle())) throw new RuntimeException("Song is not playing") ;
        audioEngine.pause() ;
    }

    public void playAllTracks(){
        if(loadedPlaylist == null) throw new RuntimeException("Playlist is not loaded") ;
        while(playStrategy.hasNext()){
            Song nextSong = playStrategy.next() ;
            IAudioOutputDevice audioOutputDevice = DeviceManager.getInstance().getCurrentOutputDevice() ;
            audioEngine.play(audioOutputDevice , nextSong) ;
        }
        System.out.println("Playlist Finished :: " + loadedPlaylist.getPlayListName()) ;
    }

    public void playNextTrack(){
        if(loadedPlaylist == null) throw new RuntimeException("Playlist is not loaded") ;
        if(playStrategy.hasNext()){
            Song nextSong = playStrategy.next() ;
            IAudioOutputDevice audioOutputDevice = DeviceManager.getInstance().getCurrentOutputDevice() ;
            audioEngine.play(audioOutputDevice , nextSong) ;
        }
        else{
            System.out.println("Playlist Finished :: " + loadedPlaylist.getPlayListName()) ;
        }
    }

    public void playPreviousTrack(){
        if(loadedPlaylist == null) throw new RuntimeException("Playlist is not loaded") ;
        if(playStrategy.hasPrevious()){
            Song previousSong = playStrategy.previous() ;
            IAudioOutputDevice audioOutputDevice = DeviceManager.getInstance().getCurrentOutputDevice() ;
            audioEngine.play(audioOutputDevice , previousSong) ;
        }
        else{
            System.out.println("Playlist Finished :: " + loadedPlaylist.getPlayListName()) ;
        }
    }



}
