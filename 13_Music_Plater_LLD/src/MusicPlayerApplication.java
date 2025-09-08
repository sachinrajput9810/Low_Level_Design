import enums.DeviceType;
import enums.PlayingStrategyType;
import managers.DeviceManager;
import managers.PlaylistManager;
import models.Song;

import java.util.ArrayList;
import java.util.List;

public class MusicPlayerApplication {
    private static MusicPlayerApplication instance = null ;
    List<Song> songList  = new ArrayList<>() ;
    private MusicPlayerApplication(){}
    public static synchronized MusicPlayerApplication getInstance(){
        if(instance == null) instance = new MusicPlayerApplication() ;
        return instance ;
    }

    public void createSongInLibrary(String title, String artist){
        Song song = new Song(title,artist) ;
        songList.add(song) ;
    }

    public Song findSongByTitle(String title){
        for(Song song : songList){
            if(song.getTitle().equals(title)) return song ;
        }
        return null ;
    }

    public void createPlayList(String playListName){
        PlaylistManager.getInstance().createPlaylist(playListName) ;
    }

    public void addSongToPlaylist(String playListName , String songTitle){
        Song song = findSongByTitle(songTitle) ;
        if(song == null) throw new RuntimeException("Song not found") ;
        PlaylistManager.getInstance().addSongToPlaylist(playListName , song) ;
    }

    public void connectAudioDevice(DeviceType deviceType){
        MusicPlayerFascade.getInstance().connect(deviceType) ;
    }

    public void selectPlayStrategy(PlayingStrategyType playingStrategyType){
        MusicPlayerFascade.getInstance().setPlayStrategy(playingStrategyType) ;
    }

    public void loadPlaylist(String playListName){
        MusicPlayerFascade.getInstance().loadPlaylist(playListName) ;
    }

    public void playSingleSong(String songTitle){
        Song song = findSongByTitle(songTitle) ;
        if(song == null) throw new RuntimeException("Song not found") ;
        MusicPlayerFascade.getInstance().playSong(song) ;
    }

    public void pauseCurrentSong(String songTitle){
        Song song = findSongByTitle(songTitle) ;
        if(song == null) throw new RuntimeException("Song not found") ;
        MusicPlayerFascade.getInstance().pauseSong(song) ;
    }

    public void playAllTracks(){
        MusicPlayerFascade.getInstance().playAllTracks() ;
    }

    public void playPreviousTrack(){
        MusicPlayerFascade.getInstance().playPreviousTrack() ;
    }

    public void playNextTrack(){
        MusicPlayerFascade.getInstance().playNextTrack() ;
    }





}
