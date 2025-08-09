package managers;

import models.PlayList;
import models.Song;

import java.util.HashMap;

public class PlaylistManager {
    private static PlaylistManager instance = null ;
    private HashMap<String  , PlayList> map ;
    private PlaylistManager(){
        map = new HashMap<>() ;
    }
    public static PlaylistManager getInstance(){
        if(instance == null){
            instance = new PlaylistManager() ;
        }
        return instance ;
    }

    public void createPlaylist(String playlistName){
        if(map.containsKey(playlistName)){
            throw new RuntimeException("Playlist already exists") ;
        }
        PlayList playList = new PlayList(playlistName) ;
        map.put(playlistName , playList) ;
    }

    public void addSongToPlaylist(String playListName, Song song){
        PlayList playList = map.get(playListName) ;
        if(playList == null) throw new RuntimeException("Playlist does not exist") ;
        playList.addSongToPlaylist(song) ;
    }
    public PlayList getPlayList(String playListName){
        if(!map.containsKey(playListName)) throw new RuntimeException("Playlist does not exist") ;
        return map.get(playListName) ;
    }
}
